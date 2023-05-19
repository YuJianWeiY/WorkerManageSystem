package com.example.workermanagesystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;

public class ModifyAWorker2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_modify_worker_name2;
    private EditText editText_modify_worker_sex2;
    private EditText editText_modify_worker_department2;
    private EditText editText_modify_worker_position2;
    private EditText editText_modify_worker_salary2;
    private EditText editText_modify_worker_phone2;
    private Button button_modify_right2;
    private Button button_modify_return3;
    private Handler handler;
    private WorkerDao workerDao;
    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_a_worker2);
        button_modify_return3=findViewById(R.id.button_modify_return3);
        button_modify_return3.setOnClickListener(this);
        editText_modify_worker_name2=findViewById(R.id.editText_modify_worker_name2);
        editText_modify_worker_sex2=findViewById(R.id.editText_modify_worker_sex2);
        editText_modify_worker_department2=findViewById(R.id.editText_modify_worker_department2);
        editText_modify_worker_position2=findViewById(R.id.editText_modify_worker_position2);
        editText_modify_worker_salary2=findViewById(R.id.editText_modify_worker_salary2);
        editText_modify_worker_phone2=findViewById(R.id.editText_modify_worker_phone2);
        button_modify_right2=findViewById(R.id.button_modify_right2);
        button_modify_right2.setOnClickListener(this);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        handler=new Handler(getMainLooper());
        workerDao=new WorkerDao();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //点击button按钮缩小，松开恢复和点击震动
        button_modify_right2.setOnTouchListener(new View.OnTouchListener() {
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
        button_modify_return3.setOnTouchListener(new View.OnTouchListener() {
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Worker worker_id=workerDao.findID2(id);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        editText_modify_worker_name2.setText(worker_id.getName());
                        editText_modify_worker_sex2.setText(worker_id.getSex());
                        editText_modify_worker_department2.setText(worker_id.getDepartment());
                        editText_modify_worker_position2.setText(worker_id.getPosition());
                        editText_modify_worker_salary2.setText(worker_id.getSalary());
                        editText_modify_worker_phone2.setText(worker_id.getPhone());
                    }
                });
            }
        }).start();
    }
    public void modify3()
    {
        final String name=editText_modify_worker_name2.getText().toString().trim();
        final String sex=editText_modify_worker_sex2.getText().toString().trim();
        final String department=editText_modify_worker_department2.getText().toString().trim();
        final String position=editText_modify_worker_position2.getText().toString().trim();
        final String salary=editText_modify_worker_salary2.getText().toString().trim();
        final String phone=editText_modify_worker_phone2.getText().toString().trim();
        if (TextUtils.isEmpty(name))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入姓名！");
            editText_modify_worker_name2.requestFocus();
            return;
        }else if (TextUtils.isEmpty(sex))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入性别！");
            editText_modify_worker_sex2.requestFocus();
            return;
        }else if (TextUtils.isEmpty(department))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入部门！");
            editText_modify_worker_department2.requestFocus();
            return;
        }else if (TextUtils.isEmpty(position))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入职位！");
            editText_modify_worker_position2.requestFocus();
            return;
        }else if (TextUtils.isEmpty(salary))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入工资！");
            editText_modify_worker_salary2.requestFocus();
            return;
        }else if (TextUtils.isEmpty(phone))
        {
            AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"请输入电话号码！");
            editText_modify_worker_phone2.requestFocus();
            return;
        }else
        {
            Intent intent=getIntent();
            String id=intent.getStringExtra("id");
            Worker worker=new Worker(id,name,sex,department,position,salary,phone);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int value=workerDao.modifyWorker(worker);
                    if(value>0)
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(ModifyAWorker2Activity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("修改成功！");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(ModifyAWorker2Activity.this,ModifyActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog alertDialog=builder.create();//根据建造器构建提醒对话框对象
                                alertDialog.show();//显示提醒对话框
                                //设计AlertDialog提醒对话框大小
                                WindowManager.LayoutParams layoutParams=alertDialog.getWindow().getAttributes();
                                layoutParams.width=700;
                                layoutParams.height=565;
                                alertDialog.getWindow().setAttributes(layoutParams);//设置AlertDialog的宽高
                                return;
                            }
                        });
                    }else
                    {
                        Looper.prepare();
                        AlertDialogUtils.AlertDialog(ModifyAWorker2Activity.this,"修改失败！");
                        Looper.loop();
                    }
                }
            }).start();
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_modify_return3)
        {
            Intent intent=new Intent(this,ModifyActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_modify_right2)
        {
            modify3();
        }
    }
}