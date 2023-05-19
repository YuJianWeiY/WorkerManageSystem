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
import android.os.Parcelable;
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
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_modify_return;
    private ListView listView_all_worker3;
    private Handler handler;
    private WorkerDao workerDao;
    private List<Worker> workerList;
    private WorkerBaseAdapter3 workerBaseAdapter3;
    private EditText editText_modify_by_id;
    private Button button_modify_by_id;
    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        button_modify_return=findViewById(R.id.button_modify_return);
        button_modify_return.setOnClickListener(this);
        workerDao=new WorkerDao();
        handler=new Handler(getMainLooper());
        listView_all_worker3=findViewById(R.id.listView_all_worker3);
        editText_modify_by_id=findViewById(R.id.editText_modify_by_id);
        button_modify_by_id=findViewById(R.id.button_modify_by_id);
        button_modify_by_id.setOnClickListener(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //点击button按钮缩小，松开恢复和点击震动
        button_modify_by_id.setOnTouchListener(new View.OnTouchListener() {
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
        button_modify_return.setOnTouchListener(new View.OnTouchListener() {
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
                workerList=workerDao.findWorkerList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(workerBaseAdapter3==null)
                        {
                            workerBaseAdapter3=new WorkerBaseAdapter3(ModifyActivity.this,workerList);
                            listView_all_worker3.setAdapter(workerBaseAdapter3);
                        }else
                        {
                            workerBaseAdapter3.setWorkerList(workerList);
                            workerBaseAdapter3.notifyDataSetChanged();
                        }
                        workerBaseAdapter3.setButtonListModifyOnClickListener(new buttonListModifyOnClickListener() {
                            @Override
                            public void buttonListModifyOnClickListener(View view,int position)
                            {
                                Worker worker=workerList.get(position);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("worker",worker);
                                Intent intent=new Intent(ModifyActivity.this,ModifyAWorkerActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();
    }
    public void modify2()
    {
        String id=editText_modify_by_id.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            AlertDialogUtils.AlertDialog(ModifyActivity.this,"请输入编号！");
            editText_modify_by_id.requestFocus();
            return;
        }else
        {
            Intent intent=new Intent(ModifyActivity.this,ModifyAWorker2Activity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_modify_return)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_modify_by_id)
        {
            final String id=editText_modify_by_id.getText().toString().trim();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Worker worker_id=workerDao.findID(id);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (worker_id!=null)
                            {
                                modify2();
                            }else
                            {
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(ModifyActivity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("您所输入的职工编号不存在，请重新输入！");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editText_modify_by_id.setText("");//清空编号编辑框内容
                                    }
                                });
                                AlertDialog alertDialog=builder.create();//根据建造器构建提醒对话框对象
                                alertDialog.show();//显示提醒对话框
                                //设计AlertDialog提醒对话框大小
                                WindowManager.LayoutParams layoutParams=alertDialog.getWindow().getAttributes();
                                layoutParams.width=700;
                                layoutParams.height=650;
                                alertDialog.getWindow().setAttributes(layoutParams);//设置AlertDialog的宽高
                                return;
                            }
                        }
                    });
                }
            }).start();
        }
    }
}