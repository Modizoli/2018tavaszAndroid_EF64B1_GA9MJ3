package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Vector;

class DrawingCanvas extends View {

    Bitmap greencar, purplecar;
    Bitmap bg;

    ShapeDrawable sd;
    Matrix m;

    Vector<ModelBase> drawMe;
    public void setDrawList(Vector<ModelBase> list ){
        drawMe = list;
    }

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        greencar = BitmapFactory.decodeResource(getResources(),R.drawable.red_car_front);
        purplecar = BitmapFactory.decodeResource( getResources(), R.drawable.car_purple_front );

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.road);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bg,0,0,null);

        for( int i = 0; i < drawMe.size(); ++i ){
            ModelBase thing = drawMe.get(i);

            switch( thing.resourceName ) {
            case "greencar":
                canvas.drawBitmap( greencar, thing.px, thing.py, null );
                break;
            }
        }

        /*
        canvas.drawBitmap(greencar,0,0,null);
        canvas.drawBitmap(greencar,100,0,null);
        canvas.drawBitmap( purplecar, 100, 100, null );
        */


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
