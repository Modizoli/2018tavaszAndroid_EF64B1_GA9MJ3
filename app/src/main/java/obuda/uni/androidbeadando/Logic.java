package obuda.uni.androidbeadando;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.List;
import java.util.Vector;

/**
 * Created by Zoltán on 4/25/2018.
 */

public class Logic extends Thread {
    boolean paused = true;
    boolean gameOver = false;

    ModelBase player;
    Vector<ModelBase> things = new Vector<ModelBase>();

    public SensorManager sm;
    float[] sensorInputValues;

    // we dont move if abs(threshold, value) < threshold
    float inputValueThreshold = 3.f;

    void togglePause(){
        paused = !paused;
    }

    void handleInput(){

    }

    void moveThings(){

    }

    // checks what the player collided with, returns null on nothing
    ModelBase checkCollision(){
        return null;
    }

    void HandleCollision( ModelBase model ){

    }

    public void run(){
        while( !gameOver ){
            if( !paused ){
                handleInput();
                moveThings();

                Log.d("msg", "What");

                ModelBase collided = checkCollision();
                if( collided != null ){
                    HandleCollision( collided );
                }
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
