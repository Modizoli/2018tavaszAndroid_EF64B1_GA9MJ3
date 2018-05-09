package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Vector;

class DrawingCanvas extends View {

    Bitmap redcar;
    Bitmap purplecar;
    Bitmap greencar;
    Bitmap fuel;
    Bitmap terrain;
    Bitmap background;
    Bitmap life;

    Bitmap redcarScaled;
    Bitmap purplecarScaled;
    Bitmap greencarScaled;
    Bitmap fuelScaled;
    Bitmap terrainScaled;
    Bitmap backgroundScaled;
    Bitmap lifeScaled;

    Bitmap road;

    Rect roadline;
    Paint roadPaint;

    Paint textPaint;
    int textsize=30;
    int highscore=0;
    int wWidth;
    int wHeight;

    boolean scaled=false;

    MediaPlayer gamemusic;

    Vector<ModelBase> drawMe;

    public void setDrawList(Vector<ModelBase> list ){

        drawMe = list;
    }

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        redcar = BitmapFactory.decodeResource(getResources(),R.drawable.car_red_front );
        purplecar = BitmapFactory.decodeResource( getResources(), R.drawable.car_purple_front );
        greencar = BitmapFactory.decodeResource( getResources(), R.drawable.car_green_front );

        fuel = BitmapFactory.decodeResource(getResources(),R.drawable.fuel);
        terrain = BitmapFactory.decodeResource(getResources(),R.drawable.tree);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.road);
        life = BitmapFactory.decodeResource(getResources(),R.drawable.playercar);

        textPaint=new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(textsize);

        roadline=new Rect();
        roadPaint=new Paint();
        roadPaint.setColor(Color.WHITE);


    }

    private void scaling(){
        backgroundScaled=Bitmap.createScaledBitmap(background,wWidth,wHeight,false);

        fuelScaled=Bitmap.createScaledBitmap(fuel,wWidth/20,wHeight/20,false);
        terrainScaled=Bitmap.createScaledBitmap(terrain,wWidth/20,wHeight/20,false);

        redcarScaled=Bitmap.createScaledBitmap(redcar,wWidth/10,wHeight/10,false);
        purplecarScaled=Bitmap.createScaledBitmap(purplecar,wWidth/10,wHeight/10,false);
        greencarScaled=Bitmap.createScaledBitmap(greencar,wWidth/10,wHeight/10,false);

        lifeScaled=Bitmap.createScaledBitmap(life,wWidth/30,wHeight/30,false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        wWidth=w;
        wHeight=h;

        scaling();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(backgroundScaled, 0, 0,null);

        canvas.drawText("Score: "+highscore,10,30,textPaint);

        for( int i = 0; i < drawMe.size(); ++i ){
            ModelBase thing = drawMe.get(i);

            switch( thing.resourceName ) {
                case "greencar":
                    canvas.drawBitmap( greencarScaled, thing.px, thing.py, null );
                    break;
                case "redcar":
                    canvas.drawBitmap( redcarScaled, thing.px, thing.py, null );
                    break;
                case "purplecar":
                    canvas.drawBitmap( purplecarScaled, thing.px, thing.py, null );
                    break;
            }
        }


    }
}
