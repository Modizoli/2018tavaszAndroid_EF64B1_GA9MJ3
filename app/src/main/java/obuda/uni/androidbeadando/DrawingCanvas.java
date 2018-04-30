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

class DrawingCanvas extends View {

    Bitmap greencar;
    Bitmap bg;

    ShapeDrawable sd;
    Matrix m;

    public DrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        greencar=BitmapFactory.decodeResource(getResources(),R.drawable.red_car_front);
        bg=BitmapFactory.decodeResource(getResources(),R.drawable.road);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bg,0,0,null);
        canvas.drawBitmap(greencar,0,0,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
