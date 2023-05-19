package com.example.workermanagesystem;

import androidx.annotation.NonNull;
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
import android.os.Message;
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

import java.util.List;

public class QueryActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView_all_worker;
    private Handler handler;
    private WorkerDao workerDao;
    private List<Worker> workerList;
    private WorkerBaseAdapter workerBaseAdapter;
    private Button button_query_return;
    private TextView textView_number_worker;
    private TextView textView_number_man;
    private TextView textView_number_woman;
    private EditText editText_query_by_id;
    private Button button_query_by_id;
    private TextView textview_query_id4;
    private TextView textview_query_name4;
    private TextView textview_query_sex4;
    private TextView textview_query_department4;
    private TextView textview_query_position4;
    private TextView textview_query_salary4;
    private TextView textview_query_phone4;
    private Vibrator vibrator;
    private final long VIBRATION_DURATION = 100; // 震动持续时间100毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        workerDao=new WorkerDao();
        handler=new Handler(getMainLooper());
        listView_all_worker=findViewById(R.id.listView_all_worker);
        button_query_return=findViewById(R.id.button_query_return);
        button_query_return.setOnClickListener(this);
        textView_number_worker=findViewById(R.id.textview_number_worker);
        textView_number_man=findViewById(R.id.textview_number_man);
        textView_number_woman=findViewById(R.id.textview_number_woman);
        editText_query_by_id=findViewById(R.id.editText_query_by_id);
        button_query_by_id=findViewById(R.id.button_query_by_id);
        button_query_by_id.setOnClickListener(this);
        textview_query_id4=findViewById(R.id.textview_query_id4);
        textview_query_name4=findViewById(R.id.textview_query_name4);
        textview_query_sex4=findViewById(R.id.textview_query_sex4);
        textview_query_department4=findViewById(R.id.textview_query_department4);
        textview_query_position4=findViewById(R.id.textview_query_position4);
        textview_query_salary4=findViewById(R.id.textview_query_salary4);
        textview_query_phone4=findViewById(R.id.textview_query_phone4);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //点击button按钮缩小，松开恢复和点击震动
        button_query_by_id.setOnTouchListener(new View.OnTouchListener() {
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
        button_query_return.setOnTouchListener(new View.OnTouchListener() {
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
                        if(workerBaseAdapter==null)
                        {
                            workerBaseAdapter=new WorkerBaseAdapter(QueryActivity.this,workerList);
                            listView_all_worker.setAdapter(workerBaseAdapter);
                        }else
                        {
                            workerBaseAdapter.setWorkerList(workerList);
                            workerBaseAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();

        //查询职工总数
        Handler handler2=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0)
                {
                    int count=(Integer)msg.obj;
                    textView_number_worker.setText("职工总数："+count+"人");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=JDBCUtils3.getWorkerNumber();
                Message msg=Message.obtain();
                msg.what=0;
                msg.obj=count;
                handler2.sendMessage(msg);
            }
        }).start();
        //查询男职工人数
        Handler handler3=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0)
                {
                    int count=(Integer)msg.obj;
                    textView_number_man.setText("     其中男："+count+"人，");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=JDBCUtils4.getWorkerMan();
                Message msg=Message.obtain();
                msg.what=0;
                msg.obj=count;
                handler3.sendMessage(msg);
            }
        }).start();
        //查询女职工人数
        Handler handler4=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0)
                {
                    int count=(Integer)msg.obj;
                    textView_number_woman.setText("女："+count+"人");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=JDBCUtils5.getWorkerWoman();
                Message msg=Message.obtain();
                msg.what=0;
                msg.obj=count;
                handler4.sendMessage(msg);
            }
        }).start();
    }
    public void query()
    {
        final String id=editText_query_by_id.getText().toString().trim();
        if(TextUtils.isEmpty(id))
        {
            AlertDialogUtils.AlertDialog(QueryActivity.this,"请输入编号！");
            editText_query_by_id.requestFocus();
            return;
        }else
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Worker worker_id=workerDao.findID2(id);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textview_query_id4.setText(id);
                            textview_query_name4.setText(worker_id.getName());
                            textview_query_sex4.setText(worker_id.getSex());
                            textview_query_department4.setText(worker_id.getDepartment());
                            textview_query_position4.setText(worker_id.getPosition());
                            textview_query_salary4.setText(worker_id.getSalary());
                            textview_query_phone4.setText(worker_id.getPhone());
                        }
                    });
                }
            }).start();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button_query_return)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button_query_by_id)
        {
            final String id=editText_query_by_id.getText().toString().trim();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Worker worker_id=workerDao.findID(id);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (worker_id!=null)
                            {
                                query();
                                editText_query_by_id.setText("");
                            }else
                            {
                                AlertDialogUtils.AlertDialog(QueryActivity.this,"该职工不存在！请重新输入！");
                                editText_query_by_id.setText("");
                                editText_query_by_id.requestFocus();
                            }
                        }
                    });
                }
            }).start();
        }
    }
}