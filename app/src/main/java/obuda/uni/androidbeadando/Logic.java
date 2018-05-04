package obuda.uni.androidbeadando;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.util.Vector;

/**
 * Created by Zoltán on 4/25/2018.
 */

public class Logic extends Thread {
    ModelFactory modelFactory = new ModelFactory();

    // set from game fragment
    public DrawingCanvas view;

    boolean paused      = true;
    boolean gameOver    = false;
    long frameRateTime  = 1666;
    float score           = 0;

    ModelBase player;
    Vector<ModelBase> things = new Vector<ModelBase>();

    public SensorManager sm;
    float[] sensorInputValues;

    // we dont move if abs(threshold, value) < threshold
    float inputValueThreshold = 3.f;

    void setPaused( boolean isPaused ){
        paused = isPaused;
    }

    void handleInput(){

    }

    void moveThings( long frameTimeMS ){
        for(int i = 0; i < things.size(); ++i){
            ModelBase t = things.get(i);

            IDriver driver = (IDriver)things.get(i);
            if( driver != null ){
                driver.drive( frameTimeMS );
            // if its not a driver its fuel. just move it down.
            } else {
                t.py += ( t.velocity * frameTimeMS ) / 1000;
            }

            if( t.py > 1000 ){
                things.remove( i );
            }
        }
    }

    // checks what the player collided with, returns null on nothing
    ModelBase checkCollision(){
        return null;
    }

    void HandleCollision( ModelBase model ){

    }

    public void run(){
        things.add(modelFactory.createModel( ModelFactory.PEACEFUL_DRIVER, 0 ));
        things.add(modelFactory.createModel( ModelFactory.PEACEFUL_DRIVER, 200 ));

        long lastFrameTime = 0;
        while( !gameOver ){
            if( !paused ){
                long now = SystemClock.uptimeMillis();

                handleInput();
                moveThings( lastFrameTime );

                ModelBase collided = checkCollision();
                    if( collided != null ) {
                        HandleCollision( collided );
                    }

                long end = SystemClock.uptimeMillis();
                lastFrameTime += ( end - now );

                score += 1000.f / lastFrameTime;

                view.postInvalidate();
            }
        }
    }

    public Logic( SensorManager sm ){
        this.sm = sm;

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged( SensorEvent event ) {
                sensorInputValues = event.values;
            }

            @Override
            public void onAccuracyChanged( Sensor sensor, int accuracy ) {}
        };

        sm.registerListener(
                sel,
                sm.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ),
                sm.SENSOR_DELAY_GAME
        );
    }
}
