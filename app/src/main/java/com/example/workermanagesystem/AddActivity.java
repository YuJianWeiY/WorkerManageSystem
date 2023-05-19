package com.example.workermanagesystem;

import androidx.annotation.RequiresApi;
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
import android.os.Build;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_add_return;
    private EditText editText_worker_id;
    private EditText editText_worker_name;
    private EditText editText_worker_sex;
    private EditText editText_worker_department;
    private EditText editText_worker_position;
    private EditText editText_worker_salary;
    private EditText editText_worker_phone;
    private Button button_add_right;

    private Handler handler;
    private WorkerDao workerDao;

    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        button_add_return=findViewById(R.id.button_add_return);//获取返回按钮实例
        editText_worker_id=findViewById(R.id.editText_worker_id);//获取编号编辑框实例
        editText_worker_name=findViewById(R.id.editText_worker_name);//获取姓名编辑框实例
        editText_worker_sex=findViewById(R.id.editText_worker_sex);//获取性别编辑框实例
        editText_worker_department=findViewById(R.id.editText_worker_department);//获取部门编辑框实例
        editText_worker_position=findViewById(R.id.editText_worker_position);//获取职位编辑框实例
        editText_worker_salary=findViewById(R.id.editText_worker_salary);//获取工资编辑框实例
        editText_worker_phone=findViewById(R.id.editText_worker_phone);//获取电话编辑框实例
        button_add_right=findViewById(R.id.button_add_right);//获取确认添加按钮实例

        button_add_return.setOnClickListener(this);//为返回按钮注册点击监听器
        button_add_right.setOnClickListener(this);//为确认添加按钮注册点击监听器

        handler=new Handler(getMainLooper());
        workerDao=new WorkerDao();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //点击button按钮缩小，松开恢复和点击震动
        button_add_return.setOnTouchListener(new View.OnTouchListener() {
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
        button_add_right.setOnTouchListener(new View.OnTouchListener() {
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

    public void add()
    {
        final String id=editText_worker_id.getText().toString().trim();
        final String name=editText_worker_name.getText().toString().trim();
        final String sex=editText_worker_sex.getText().toString().trim();
        final String department=editText_worker_department.getText().toString().trim();
        final String position=editText_worker_position.getText().toString().trim();
        final String salary=editText_worker_salary.getText().toString().trim();
        final String phone=editText_worker_phone.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入编号！");
            editText_worker_id.requestFocus();
            return;
        }else if (TextUtils.isEmpty(name))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入姓名！");
            editText_worker_name.requestFocus();
            return;
        }else if (TextUtils.isEmpty(sex))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入性别！");
            editText_worker_sex.requestFocus();
            return;
        }else if (TextUtils.isEmpty(department))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入部门！");
            editText_worker_department.requestFocus();
            return;
        }else if (TextUtils.isEmpty(position))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入职位！");
            editText_worker_position.requestFocus();
            return;
        }else if (TextUtils.isEmpty(salary))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入工资！");
            editText_worker_salary.requestFocus();
            return;
        }else if (TextUtils.isEmpty(phone))
        {
            AlertDialogUtils.AlertDialog(AddActivity.this,"请输入电话号码！");
            editText_worker_phone.requestFocus();
            return;
        }else
        {
            final Worker worker=new Worker();
            worker.setId(id);
            worker.setName(name);
            worker.setSex(sex);
            worker.setDepartment(department);
            worker.setPosition(position);
            worker.setSalary(salary);
            worker.setPhone(phone);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int value=workerDao.addWorker(worker);
                    if(value>0)
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("添加成功！");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(AddActivity.this,MainActivity.class);
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
                        AlertDialogUtils.AlertDialog(AddActivity.this,"添加失败！");
                        Looper.loop();
                    }
                }
            }).start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button_add_return)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_add_right)
        {
            final String id=editText_worker_id.getText().toString().trim();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Worker worker_id=workerDao.findID(id);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (worker_id!=null)
                            {
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("您所输入的职工编号已存在，请重新输入！");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editText_worker_id.setText("");//清空编号编辑框内容
                                        editText_worker_name.setText("");//清空姓名编辑框内容
                                        editText_worker_sex.setText("");//清空性别编辑框内容
                                        editText_worker_department.setText("");//清空部门编辑框内容
                                        editText_worker_position.setText("");//清空职位编辑框内容
                                        editText_worker_salary.setText("");//清空工资编辑框内容
                                        editText_worker_phone.setText("");//清空电话编辑框内容
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
                            }else
                            {
                                add();
                            }
                        }
                    });
                }
            }).start();
        }
    }
}