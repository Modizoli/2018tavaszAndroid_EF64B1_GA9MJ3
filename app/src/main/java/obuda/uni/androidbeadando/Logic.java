package obuda.uni.androidbeadando;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;

import java.util.Vector;

/**
 * Created by Zoltán on 4/25/2018.
 */

public class Logic extends Thread {
    ModelFactory modelFactory = new ModelFactory();

    boolean paused = true;
    boolean gameOver = false;
    long frameRateTime = 1666;

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
        Log.d("test", "TESTING");
    }

    void moveThings(){
        for(int i = 0; i < things.size(); ++i){
            ModelBase t = things.get(i);

            IDriver driver = (IDriver)things.get(i);
            if( driver != null ){
                driver.drive();
            // if its not a driver its fuel. just move it down.
            } else {
                t.py += t.velocity;
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

        long lastFrameTime = 0;
        while( !gameOver ){
            if( !paused ){
                long now = SystemClock.uptimeMillis();

                while( lastFrameTime >= frameRateTime ) {
                    handleInput();
                    moveThings();

                    ModelBase collided = checkCollision();
                    if( collided != null ) {
                        HandleCollision( collided );
                    }

                    lastFrameTime -= frameRateTime;
                }

                long end = SystemClock.uptimeMillis();
                lastFrameTime += ( end - now );
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
