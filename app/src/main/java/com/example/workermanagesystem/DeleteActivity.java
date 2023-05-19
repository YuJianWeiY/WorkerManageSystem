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
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_delete_return;
    private ListView listView_all_worker2;
    private Handler handler;
    private WorkerDao workerDao;
    private List<Worker> workerList;
    private WorkerBaseAdapter2 workerBaseAdapter2;
    private EditText editText_delete_by_id;
    private Button button_delete_by_id;
    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        button_delete_return=findViewById(R.id.button_delete_return);
        listView_all_worker2=findViewById(R.id.listView_all_worker2);
        workerDao=new WorkerDao();
        handler=new Handler(getMainLooper());
        editText_delete_by_id=findViewById(R.id.editText_delete_by_id);
        button_delete_by_id=findViewById(R.id.button_delete_by_id);

        button_delete_return.setOnClickListener(this);
        button_delete_by_id.setOnClickListener(this);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //点击button按钮缩小，松开恢复和点击震动
        button_delete_return.setOnTouchListener(new View.OnTouchListener() {
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
        button_delete_by_id.setOnTouchListener(new View.OnTouchListener() {
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
                        if(workerBaseAdapter2==null)
                        {
                            workerBaseAdapter2=new WorkerBaseAdapter2(DeleteActivity.this,workerList);
                            listView_all_worker2.setAdapter(workerBaseAdapter2);
                        }else
                        {
                            workerBaseAdapter2.setWorkerList(workerList);
                            workerBaseAdapter2.notifyDataSetChanged();
                        }
                        workerBaseAdapter2.setButtonListDeleteOnClickListener(new buttonListDeleteOnClickListener() {
                            @Override
                            public void buttonListDeleteOnClickListener(View view, int position) {
                                final Worker worker=workerList.get(position);
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(DeleteActivity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("您确定要删除职工编号为"+worker.getId()+"的职工吗？");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                final int value=workerDao.deleteWorker(worker.getId());
                                                handler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                workerList=workerDao.findWorkerList();
                                                                handler.post(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        if(workerBaseAdapter2==null)
                                                                        {
                                                                            workerBaseAdapter2=new WorkerBaseAdapter2(DeleteActivity.this,workerList);
                                                                            listView_all_worker2.setAdapter(workerBaseAdapter2);
                                                                        }else
                                                                        {
                                                                            workerBaseAdapter2.setWorkerList(workerList);
                                                                            workerBaseAdapter2.notifyDataSetChanged();
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                    }
                                                });
                                            }
                                        }).start();
                                    }
                                });
                                builder.setNegativeButton("取消",null);
                                AlertDialog alertDialog=builder.create();//根据建造器构建提醒对话框对象
                                alertDialog.show();//显示提醒对话框
                                //设计AlertDialog提醒对话框大小
                                WindowManager.LayoutParams layoutParams=alertDialog.getWindow().getAttributes();
                                layoutParams.width=700;
                                layoutParams.height=565;
                                alertDialog.getWindow().setAttributes(layoutParams);//设置AlertDialog的宽高
                            }
                        });
                    }
                });
            }
        }).start();
    }
    public void delete()
    {
        final String id=editText_delete_by_id.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            AlertDialogUtils.AlertDialog(DeleteActivity.this,"请输入编号！");
            editText_delete_by_id.requestFocus();
            return;
        }else
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int value=workerDao.deleteWorker(id);
                    if(value>0)
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //创建提醒对话框的建造器
                                AlertDialog.Builder builder=new AlertDialog.Builder(DeleteActivity.this);
                                //设计对话框标题图标
                                builder.setIcon(R.mipmap.ic_launcher);
                                //设置对话框标题文本
                                builder.setTitle("尊敬的用户");
                                //设置对话框内容文本
                                builder.setMessage("删除成功！");
                                //设置对话框的肯定按钮文本及其点击监听器
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                workerList=workerDao.findWorkerList();
                                                handler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if(workerBaseAdapter2==null)
                                                        {
                                                            workerBaseAdapter2=new WorkerBaseAdapter2(DeleteActivity.this,workerList);
                                                            listView_all_worker2.setAdapter(workerBaseAdapter2);
                                                        }else
                                                        {
                                                            workerBaseAdapter2.setWorkerList(workerList);
                                                            workerBaseAdapter2.notifyDataSetChanged();
                                                        }
                                                    }
                                                });
                                            }
                                        }).start();
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
                        AlertDialogUtils.AlertDialog(DeleteActivity.this,"删除失败！该职工编号不存在！请重新输入");
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
        if(v.getId()==R.id.button_delete_return)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_delete_by_id)
        {
            delete();
            editText_delete_by_id.setText("");
        }
    }
}