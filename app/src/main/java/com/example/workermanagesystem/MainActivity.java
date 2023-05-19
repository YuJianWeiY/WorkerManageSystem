package com.example.workermanagesystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_add;
    private Button button_query;
    private Button button_delete;
    private Button button_modify;

    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add=findViewById(R.id.button_add);//获取添加按钮实例
        button_query=findViewById(R.id.button_query);//获取查询按钮实例
        button_delete=findViewById(R.id.button_delete);//获取删除按钮实例
        button_modify=findViewById(R.id.button_modify);//获取修改按钮实例

        button_add.setOnClickListener(this);//为添加按钮注册点击监听器
        button_query.setOnClickListener(this);//为查询按钮注册点击监听器
        button_delete.setOnClickListener(this);//为删除按钮注册点击监听器
        button_modify.setOnClickListener(this);//为修改按钮注册点击监听

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //点击button按钮缩小，松开恢复和点击震动
        button_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //缩小按钮
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        //恢复按钮
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        //点击button按钮缩小，松开恢复和点击震动
        button_query.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //缩小按钮
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        //恢复按钮
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        //点击button按钮缩小，松开恢复和点击震动
        button_delete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //缩小按钮
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        //恢复按钮
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        //点击button按钮缩小，松开恢复和点击震动
        button_modify.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //缩小按钮
                        ScaleAnimation shrinkAnimation=new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        shrinkAnimation.setDuration(100);
                        shrinkAnimation.setFillAfter(true);
                        v.startAnimation(shrinkAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        //恢复按钮
                        ScaleAnimation restoreAnimation=new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        restoreAnimation.setDuration(100);
                        restoreAnimation.setFillAfter(true);
                        v.startAnimation(restoreAnimation);
                        break;
                }
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        // 按下时开始震动
                        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
                        break;
                    case MotionEvent.ACTION_UP:
                        // 松开时停止震动
                        vibrator.cancel();
                        break;
                }
                return false;
            }
        });

        //保持竖屏
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double angle = Math.atan2(y, x) * 180 / Math.PI;
                if (angle < -45 && angle > -135) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                } else if (angle > 45 && angle < 135) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button_add)
        {
            Intent intent=new Intent(this,AddActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_query)
        {
            Intent intent=new Intent(this,QueryActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_delete)
        {
            Intent intent=new Intent(this,DeleteActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_modify)
        {
            Intent intent=new Intent(this,ModifyActivity.class);
            startActivity(intent);
        }
    }
}