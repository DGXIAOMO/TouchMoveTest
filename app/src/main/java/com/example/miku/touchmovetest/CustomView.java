package com.example.miku.touchmovetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by miku on 2017/4/13.
 */
public class CustomView extends ViewGroup {
    private int lastX,lastY;
    private Context context;
    Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
    Canvas bcanvas = new Canvas(bitmap);
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
         Paint paint = new Paint();
         paint.setColor(Color.RED);
        // bcanvas.drawCircle(100,100,100, paint);
         //canvas.drawCircle(100,100,100,paint);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            switch (i) {
                case 0:
                child.layout(0, 0, width, height);
                    break;
                case 1:
                   child.layout(width, 0, width*2, height);
                    break;
            }
            Move(child);

        }
    }
    private void Move(final View child)
    {
        child.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int offsetX = x - lastX;
                        int offsetY = y - lastY;
                        child.layout(child.getLeft() + offsetX, child.getTop() + offsetY, child.getRight() + offsetX, child.getBottom() + offsetY);
                        break;
                }
                return true;
            }
        });
    }

    /*private void Draw(final int i)
    {
        Paint paint = new Paint();
          switch (i)
          {
              case 0:
                  paint.setColor(Color.RED);
                  canvas.drawCircle(100,100,100,paint);
                  break;
              case 1:
                  break;
          }
    }
    */
    public CustomView(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

}
