package com.liqi.sensordemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class
MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor acc_sensor;
    private Sensor mag_sensor;
    //加速度传感器数据
    float accValues[]=new float[3];
    //地磁传感器数据
    float magValues[]=new float[3];
    //旋转矩阵，用来保存磁场和加速度的数据
    float r[]=new float[9];
    //模拟方向传感器的数据（原始数据为弧度）
    float values[]=new float[3];
    TextView show_change=null;
    private View bg;
//    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg = (View) findViewById(R.id.bgiv);
        RainDrawer r = new RainDrawer(this,false);
        r.setSize(500,500);
        ((BG1)bg).setDrawable(r);
        PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("translationX", 0, -600);
        PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("translationY", 0, 600);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(bg, xHolder, yHolder);
        animator.setDuration(20000);
        animator.start();

//        drawable = new SunDrawable(this);
//        bg.setBackground(drawable);
        show_change=(TextView) findViewById(R.id.show_change);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acc_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //给传感器注册监听：
        sensorManager.registerListener(this, acc_sensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, mag_sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            accValues=event.values.clone();//这里是对象，需要克隆一份，否则共用一份数据
        }
        else if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            magValues=event.values.clone();//这里是对象，需要克隆一份，否则共用一份数据
        }
        /**public static boolean getRotationMatrix (float[] R, float[] I, float[] gravity, float[] geomagnetic)
         * 填充旋转数组r
         * r：要填充的旋转数组
         * I:将磁场数据转换进实际的重力坐标中 一般默认情况下可以设置为null
         * gravity:加速度传感器数据
         * geomagnetic：地磁传感器数据
         */
        SensorManager.getRotationMatrix(r, null, accValues, magValues);
        /**
         * public static float[] getOrientation (float[] R, float[] values)
         * R：旋转数组
         * values ：模拟方向传感器的数据
         */

        SensorManager.getOrientation(r, values);
        show_change.setText(values[1]+"");

        //将弧度转化为角度后输出
//        StringBuffer buff=new StringBuffer();
//        for(float value:values){
//            value=(float) Math.toDegrees(value);
//            buff.append(value+"  ");
//        }
//        show_change.setText(buff.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
