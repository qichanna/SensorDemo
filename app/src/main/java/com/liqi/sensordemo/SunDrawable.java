package com.liqi.sensordemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by liqi7 on 2017/5/18.
 */

public class SunDrawable extends Drawable implements Animatable{

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;

    private Bitmap c1;

    public SunDrawable(Activity activity) {
        this.mPaint1 = new Paint();
        this.mPaint2 = new Paint();
        this.mPaint3 = new Paint();
        this.mPaint4 = new Paint();
        this.mPaint1.setAntiAlias(true);
        this.mPaint2.setAntiAlias(true);
        this.mPaint3.setAntiAlias(true);
        this.mPaint4.setAntiAlias(true);
        this.mPaint1.setColor(Color.GRAY);
        this.mPaint2.setColor(Color.RED);
        this.mPaint3.setColor(Color.GREEN);
        this.mPaint4.setColor(Color.BLUE);

        c1 = BitmapFactory.decodeResource(activity.getResources(),R.drawable.b1);
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawCircle(1080,0,600,mPaint4);
//        canvas.drawCircle(1080,0,400,mPaint3);
//        canvas.drawCircle(1080,0,250,mPaint2);
//        canvas.drawCircle(1080,0,100,mPaint1);
        canvas.drawBitmap(c1,980,0,mPaint1);

        PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("translationX", 0, -600);
        PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("translationY", 0, 600);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, xHolder, yHolder);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
