package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Vector;

class DrawingCanvas extends View {

    public static final int CAR_RATIO_WIDTH = 10;
    public static final int CAR_RATIO_HEIGHT = 10;

    public static final int TERRAIN_RATIO_WIDTH = 20;
    public static final int TERRAIN_RATIO_HEIGHT = 20;

    public static final int LIFE_RATIO_WIDTH = 15;
    public static final int LIFE_RATIO_HEIGHT = 25;

    public static final int FUEL_RATIO_WIDTH = 10;
    public static final int FUEL_RATIO_HEIGHT = 20;

    public static final int ROAD_LINE_RATIO_WIDTH = 25;
    public static final int ROAD_LINE_RATIO_HEIGHT = 20;

    Bitmap redcar;
    Bitmap yellowcar;
    Bitmap greencar;
    Bitmap fuel;
    Bitmap terrain;
    Bitmap background;
    Bitmap life;
    Bitmap player;
    Bitmap roadline;

    Bitmap redcarScaled;
    Bitmap yellowcarScaled;
    Bitmap greencarScaled;
    Bitmap fuelScaled;
    Bitmap terrainScaled;
    Bitmap backgroundScaled;
    Bitmap lifeScaled;
    Bitmap playerScaled;
    Bitmap roadlineScaled;

    Paint textPaint;
    int textsize = 30;
    String text="Score: ";

    Paint gameovertextPaint;
    int gameovertextsize=60;
    String gameoverText="GAME OVER";

    int highscore = 0;
    int wWidth;
    int wHeight;

    boolean scaled = false;
    public Logic logic;

    MediaPlayer gamemusic;

    Vector<ModelBase> drawMe;

    public void setDrawList(Vector<ModelBase> list) {

        drawMe = list;
    }

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gamemusic = MediaPlayer.create(getContext(), R.raw.game);
        gamemusic.start();
        gamemusic.isLooping();

        redcar = BitmapFactory.decodeResource(getResources(), R.drawable.car_red_front);
        yellowcar = BitmapFactory.decodeResource(getResources(), R.drawable.car_yellow_front);
        greencar = BitmapFactory.decodeResource(getResources(), R.drawable.car_green_front);
        player = BitmapFactory.decodeResource(getResources(), R.drawable.playercar);

        fuel = BitmapFactory.decodeResource(getResources(), R.drawable.fuel);
        terrain = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.road);
        life = BitmapFactory.decodeResource(getResources(), R.drawable.life);
        roadline=BitmapFactory.decodeResource(getResources(), R.drawable.roadline);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(textsize);

        gameovertextPaint = new Paint();
        gameovertextPaint.setColor(Color.BLACK);
        gameovertextPaint.setTextSize(gameovertextsize);
        gameovertextPaint.setTypeface(Typeface.create("",Typeface.BOLD));
    }

    private void scaling() {
        backgroundScaled = Bitmap.createScaledBitmap(background, wWidth, wHeight, false);

        fuelScaled = Bitmap.createScaledBitmap(fuel, wWidth / FUEL_RATIO_WIDTH, wHeight / FUEL_RATIO_HEIGHT, false);
        terrainScaled = Bitmap.createScaledBitmap(terrain, wWidth / TERRAIN_RATIO_WIDTH, wHeight / TERRAIN_RATIO_HEIGHT, false);
        roadlineScaled = Bitmap.createScaledBitmap(terrain, wWidth / ROAD_LINE_RATIO_WIDTH, wHeight / ROAD_LINE_RATIO_HEIGHT, false);

        redcarScaled = Bitmap.createScaledBitmap(redcar, wWidth / CAR_RATIO_WIDTH, wHeight / CAR_RATIO_HEIGHT, false);
        yellowcarScaled = Bitmap.createScaledBitmap(yellowcar, wWidth / CAR_RATIO_WIDTH, wHeight / CAR_RATIO_HEIGHT, false);
        greencarScaled = Bitmap.createScaledBitmap(greencar, wWidth / CAR_RATIO_WIDTH, wHeight / CAR_RATIO_HEIGHT, false);
        playerScaled = Bitmap.createScaledBitmap(player, wWidth / CAR_RATIO_WIDTH, wHeight / CAR_RATIO_HEIGHT, false);

        lifeScaled = Bitmap.createScaledBitmap(life, wWidth / LIFE_RATIO_WIDTH, wHeight / LIFE_RATIO_HEIGHT, false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        wWidth = w;
        wHeight = h;
        logic.setwWidth(w);
        logic.setwHeigth(h);

        scaling();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


            canvas.drawBitmap(backgroundScaled, 0, 0, null);

            canvas.drawBitmap(greencarScaled, logic.player.px,
                    logic.player.py, null);

            synchronized (logic.thingsLock) {
                for (int i = 0; i < logic.things.size(); ++i) {
                    ModelBase thing = logic.things.get(i);

                    switch (thing.resourceName) {
                        case "greencar":
                            canvas.drawBitmap(greencarScaled, thing.px, thing.py, null);
                            break;
                        case "redcar":
                            canvas.drawBitmap(redcarScaled, thing.px, thing.py, null);
                            break;
                        case "yellowcar":
                            canvas.drawBitmap(yellowcarScaled, thing.px, thing.py, null);
                            break;
                        case "fuel":
                            canvas.drawBitmap(fuelScaled, thing.px, thing.py, null);
                            break;

                        case "roadline":
                            canvas.drawBitmap(playerScaled, thing.px, thing.py, null);
                            break;
                        case "terrain":
                            canvas.drawBitmap(playerScaled, thing.px, thing.py, null);
                            break;
                    }
                }
            }

            canvas.drawBitmap(playerScaled, logic.player.px, logic.player.py, null);

            for (int i = 0; i <= logic.player.hp; i++) {
                canvas.drawBitmap(lifeScaled, wWidth - lifeScaled.getWidth()*i, 10, null);
            }

            canvas.drawText(text + logic.score, 10, 30, textPaint);

        if(logic.gameOver)
            canvas.drawText(gameoverText,wWidth/4,wHeight/3,gameovertextPaint);

    }
}


