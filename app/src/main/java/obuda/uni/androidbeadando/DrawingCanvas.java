package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

class DrawingCanvas extends View {

    Bitmap redcar;
    Bitmap purplecar;
    Bitmap greencar;
    Bitmap fuel;
    Bitmap terrain;
    Bitmap background;

    Bitmap redcarScaled;
    Bitmap purplecarScaled;
    Bitmap greencarScaled;
    Bitmap fuelScaled;
    Bitmap terrainScaled;
    Bitmap backgroundScaled;

    Paint textPaint;
    int textsize=30;
    int highscore=0;

    boolean scaled=false;
    public Logic logic;

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        redcar = BitmapFactory.decodeResource(getResources(),R.drawable.car_red_front );
        purplecar = BitmapFactory.decodeResource( getResources(), R.drawable.car_purple_front );
        greencar = BitmapFactory.decodeResource( getResources(), R.drawable.car_green_front );

        fuel = BitmapFactory.decodeResource(getResources(),R.drawable.fuel);
        terrain = BitmapFactory.decodeResource(getResources(),R.drawable.tree);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.road);

        textPaint=new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(textsize);

    }

    private void scaling(){
        backgroundScaled=Bitmap.createScaledBitmap(background,getWidth(),getHeight(),false);

        fuelScaled=Bitmap.createScaledBitmap(fuel,getWidth()/20,getHeight()/20,false);
        terrainScaled=Bitmap.createScaledBitmap(terrain,getWidth()/20,getHeight()/20,false);

        redcarScaled=Bitmap.createScaledBitmap(redcar,getWidth()/10,getHeight()/10,false);
        purplecarScaled=Bitmap.createScaledBitmap(purplecar,getWidth()/10,getHeight()/10,false);
        greencarScaled=Bitmap.createScaledBitmap(greencar,getWidth()/10,getHeight()/10,false);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!scaled) {
            scaled=true;
            scaling();
        }

        canvas.drawBitmap(backgroundScaled, 0, 0,null);

        canvas.drawBitmap( greencarScaled, logic.player.px,
                logic.player.py, null);

        for( int i = 0; i < logic.things.size(); ++i ){
            synchronized( logic.thingsLock ) {
                ModelBase thing = logic.things.get( i );

                switch( thing.resourceName ) {
                    case "greencar":
                        canvas.drawBitmap( greencarScaled, thing.px, thing.py, null );
                        break;
                }
            }
        }

        canvas.drawText("Score: "+highscore,10,30,textPaint);
    }
}
