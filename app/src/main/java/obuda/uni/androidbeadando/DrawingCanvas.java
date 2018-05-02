package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

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

    Vector<ModelBase> drawMe;

    public void setDrawList(Vector<ModelBase> list ){

        drawMe = list;
    }

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        redcar = BitmapFactory.decodeResource(getResources(),R.drawable.red_car_front );
        //purplecar = BitmapFactory.decodeResource( getResources(), R.drawable.car_purple_front );
        greencar = BitmapFactory.decodeResource( getResources(), R.drawable.car_green_front );

        fuel = BitmapFactory.decodeResource(getResources(),R.drawable.fuel);
        //terrain = BitmapFactory.decodeResource(getResources(),R.drawable.tree);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.road);
    }

    private void scaling(){
        backgroundScaled=Bitmap.createScaledBitmap(background,getWidth(),getHeight(),false);

        fuelScaled=Bitmap.createScaledBitmap(fuel,getWidth()/20,getHeight()/20,false);
        //terrainScaled=Bitmap.createScaledBitmap(terrain,getWidth()/20,getHeight()/20,false);


        redcarScaled=Bitmap.createScaledBitmap(redcar,getWidth()/10,getHeight()/10,false);
        //purplecarScaled=Bitmap.createScaledBitmap(purplecar,getWidth()/10,getHeight()/10,false);
        greencarScaled=Bitmap.createScaledBitmap(greencar,getWidth()/10,getHeight()/10,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scaling();

        canvas.drawBitmap(backgroundScaled,0,0,null);

        for( int i = 0; i < drawMe.size(); ++i ){
            ModelBase thing = drawMe.get(i);

            switch( thing.resourceName ) {
            case "greencar":
                canvas.drawBitmap( greencarScaled, thing.px, thing.py, null );
                break;
            }
        }

        /*
        canvas.drawBitmap(greencar,0,0,null);
        canvas.drawBitmap(greencar,100,0,null);
        canvas.drawBitmap( purplecar, 100, 100, null );
        */


    }
}
