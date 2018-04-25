package obuda.uni.androidbeadando;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

class DrawingCanvas extends View {

    Bitmap greencar;

    public DrawingCanvas(Context context) {
        super(context);
        //greencar= BitmapFactory.decodeResource(getResources(),R.drawable.car_green_front);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.WHITE);
        //canvas.drawBitmap(greencar,(canvas.getWidth()/2),0,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
