package com.example.workermanagesystem;

import android.content.Context;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogUtils {
    public static void AlertDialog(Context context,String message)
    {
        //弹出提醒对话框，提醒用户用户名不能为空
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("尊敬的用户");
        builder.setMessage(message);
        builder.setPositiveButton("好的",null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        //设计AlertDialog提醒对话框大小
        WindowManager.LayoutParams layoutParams=alertDialog.getWindow().getAttributes();
        layoutParams.width=700;
        layoutParams.height=565;
        alertDialog.getWindow().setAttributes(layoutParams);//设置AlertDialog的宽高
    }
}
