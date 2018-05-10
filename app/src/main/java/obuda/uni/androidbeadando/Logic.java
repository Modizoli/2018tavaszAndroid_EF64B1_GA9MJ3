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
    float score         = 0;
    float velocityMultiplyer = 1.f;

    int wWidth;
    int wHeight;

    ModelBase player;
    Vector<ModelBase> things = new Vector<ModelBase>();
    Object thingsLock = new Object();

    public SensorManager sm;
    float[] sensorInputValues = new float[]{0.f, 0.f, 0.f};

    // we dont move if abs(threshold, value) < threshold
    float inputValueThreshold = 3.f;

    public void setwWidth(int width){
        this.wWidth=width;
        player.px = width / 2;
    }
    public void setwHeigth(int height){
        this.wHeight=height;
        player.py = height * 0.9f;
    }

    void setPaused( boolean isPaused ){
        paused = isPaused;
    }

    void handleInput( float frameTimeMS ){
        float x = sensorInputValues[0];

        if(Math.abs(x) > inputValueThreshold){
            // go right
            if( x < 0 ){
                if( player.px > 0 ) {
                    player.px += 0.001 * frameTimeMS;
                }

            // go left
            } else {
                if(player.px < 250 )
                    player.px -= 0.001 * frameTimeMS;
            }
        }
    }

    void moveThings( long frameTimeMS ){
        synchronized( thingsLock ) {

            for( int i = 0; i < things.size(); ++i ) {
                ModelBase t = things.get( i );

                IDriver driver = ( IDriver ) things.get( i );
                if( driver != null ) {
                    driver.drive( frameTimeMS, velocityMultiplyer, player );
                    // if its not a driver its fuel. just move it down.
                } else {
                    t.py += ( t.velocity * frameTimeMS ) / 1000;
                }

                if( t.py > wHeight || t.hp <= 0 ) {
                    things.remove( i );
                }
            }
        }
    }

    // checks what the player collided with, returns null on nothing
    ModelBase checkCollision(){
        return null;
    }

    void HandleCollision( ModelBase model ){
        model.hp--;

        if(model instanceof FuelModel){
            player.hp++;
        } else {
            player.hp--;

            if(player.hp == 0){
                gameOver = true;
                paused = true;
            }
        }
    }

    public void run(){
        long lastFrameTime = 0;
        while( !gameOver ){
            if( !paused ){
                long now = SystemClock.uptimeMillis();

                handleInput( lastFrameTime );
                moveThings( lastFrameTime );

                ModelBase collided = checkCollision();
                    if( collided != null ) {
                        HandleCollision( collided );
                    }

                long end = SystemClock.uptimeMillis();
                lastFrameTime += ( end - now );

                score += lastFrameTime / 1000.f;

                view.postInvalidate();
            }
        }
    }

    public void setupPlayer(){
        player = new ModelBase();
        player.px = 0;
        player.resourceName = "greencar";
        player.hp = 3;
    }

    public Logic( SensorManager sm ){
        setupPlayer();

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
