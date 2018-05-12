package obuda.uni.androidbeadando;

import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.constraint.solver.widgets.Rectangle;
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

    boolean paused = true;
    boolean gameOver = false;
    int score = 0;
    float velocityMultiplyer = 1.f;
    long playTime = 0;
    int lastSpawnTime = -1;

    int wWidth;
    int wHeight;

    MediaPlayer crashsound;

    ModelBase player;
    Vector<ModelBase> things = new Vector<ModelBase>();
    Object thingsLock = new Object();

    public SensorManager sm;
    float[] sensorInputValues = new float[]{0.f, 0.f, 0.f};

    // we dont move if abs(threshold, value) < threshold
    float inputValueThreshold = 4.f;

<<<<<<< HEAD
=======
    void setThingsWidth(){
        int carWidth = wWidth / DrawingCanvas.CAR_RATIO_WIDTH;
        int fuelWidth = wWidth / DrawingCanvas.FUEL_RATIO_WIDTH;

        // existing
        for(int i = 0; i < things.size(); ++i){
            ModelBase thing = things.get(i);

            if( thing instanceof FuelModel){
                thing.width = fuelWidth;
            } else if (thing instanceof IDriver ){
                thing.width = carWidth;
            }
        }

        // factory
        modelFactory.models.get( ModelFactory.FOLLOWER_DRIVER ).width = carWidth;
        modelFactory.models.get( ModelFactory.RECKLESS_DRIVER ).width = carWidth;
        modelFactory.models.get( ModelFactory.PEACEFUL_DRIVER ).width = carWidth;
        modelFactory.models.get( ModelFactory.FUEL ).width = fuelWidth;
    }

    void setThingsHeight(){
        int carHeight = wHeight / DrawingCanvas.CAR_RATIO_HEIGHT;
        int fuelHeight = wHeight / DrawingCanvas.FUEL_RATIO_HEIGHT;

        // existing
        for(int i = 0; i < things.size(); ++i){
            ModelBase thing = things.get(i);

            if( thing instanceof FuelModel){
                thing.height = fuelHeight;
            } else if (thing instanceof IDriver ){
                thing.height = carHeight;
            }
        }

        // factory
        modelFactory.models.get( ModelFactory.FOLLOWER_DRIVER ).height = carHeight;
        modelFactory.models.get( ModelFactory.RECKLESS_DRIVER ).height = carHeight;
        modelFactory.models.get( ModelFactory.PEACEFUL_DRIVER ).height = carHeight;
        modelFactory.models.get( ModelFactory.FUEL ).height = fuelHeight;
    }

>>>>>>> dev
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
                int maxRight = wWidth - player.width;

                if( player.px < maxRight ) {
                    player.px += 0.001 * frameTimeMS;

                    // correct overdriving
                    if(player.px > maxRight){
                        player.px = maxRight;
                    }
                }

            // go left
            } else {
                if(player.px > 0 )
                    player.px -= 0.001 * frameTimeMS;

                if(player.px < 0) {
                    player.px = 0;
                }
            }
        }
    }

    void moveThings( long frameTimeMS ){
        synchronized( thingsLock ) {
            for( int i = 0; i < things.size(); ++i ) {
                ModelBase t = things.get( i );

                if( t instanceof IDriver ) {
                    IDriver driver = (IDriver)t;
                    driver.drive( frameTimeMS, velocityMultiplyer, player );

                // if its not a driver its fuel. just move it down.
                } else {
                    t.py += t.velocity * velocityMultiplyer * frameTimeMS;
                }

                if( t.py > wHeight || t.hp <= 0 ) {
                    things.remove( i );
                }
            }
        }
    }

    // checks what the player collided with, returns null on nothing
    ModelBase checkCollision(){
        Rect prect = new Rect();
        prect.top = (int)player.py;
        prect.left = (int)player.px;
        prect.bottom = (int)player.py + player.height;
        prect.right = (int)player.py + player.width;


        for(int i = 0; i < things.size(); ++i){
            ModelBase thing = things.get( i );

            Rect trect = new Rect();
            trect.top = (int)thing.py;
            trect.left = (int)thing.px;
            trect.bottom = (int)thing.py + thing.height;
            trect.right = (int)thing.py + thing.width;

            if(prect.intersect( trect )){
                crashsound.start();
                return thing;
            }
        }

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

    void spawnThings(){
        int currentSec = (int)(playTime / 1000);

        if(currentSec > 2 + lastSpawnTime ){
            // where to spawn the new thing between [10%, 80%] width
            int thingPx = (int)((wWidth * 0.1) + ( Math.random() * (wWidth * 0.7) ));

            ModelBase thing;
            // every 10 second spawn a fuel barrel
            if(currentSec % 10 == 0) {
                thing = modelFactory.createModel( ModelFactory.FUEL, thingPx );
            } else {
                // floor a number between [0.5 and 3.5] so it gives a number between 0 and 3
                int type = (int)(0.5f + ( 3 * Math.random() ));
                thing = modelFactory.createModel( type, thingPx );
            }

            things.add( thing );
            lastSpawnTime = currentSec;
        }
    }

    void updateSpeed(){
        velocityMultiplyer = ( 1.f + score / 10000.f );
    }

    public void run(){
        long lastFrameTime = 0;
        while( !gameOver ){
            if( !paused ){
                long start = SystemClock.uptimeMillis();

                handleInput( lastFrameTime );
                moveThings( lastFrameTime );

                spawnThings();
                updateSpeed();

                ModelBase collided = checkCollision();
                if( collided != null ) {
                    HandleCollision( collided );
                }

                long end = SystemClock.uptimeMillis();
                lastFrameTime = ( end - start );

                playTime += lastFrameTime;
                score = ( int ) (playTime / 100);

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
