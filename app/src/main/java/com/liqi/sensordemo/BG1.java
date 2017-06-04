package com.liqi.sensordemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liqi7 on 2017/5/24.
 */

public class BG1 extends View {

    private Drawable bg;
    private RainDrawer rain;

    public BG1(Context context) {
        this(context,null);
    }

    public BG1(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public BG1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawable(RainDrawer rain){
        this.rain = rain;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rain.draw(canvas,128);
        super.onDraw(canvas);
    }
}
