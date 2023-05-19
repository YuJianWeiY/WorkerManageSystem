package com.example.workermanagesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WorkerBaseAdapter3 extends BaseAdapter {
    private Context context;//声明一个上下文对象
    private List<Worker> workerList;//声明一个职工信息列表
    private buttonListModifyOnClickListener buttonListModifyOnClickListener;
    private Button button_list_modify;
    public WorkerBaseAdapter3(){}
    //职工适配器的构造方法，传入上下文与职工列表
    public WorkerBaseAdapter3(Context context,List<Worker> workerList)
    {
        this.context=context;
        this.workerList=workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    public void setButtonListModifyOnClickListener(buttonListModifyOnClickListener buttonListModifyOnClickListener) {
        this.buttonListModifyOnClickListener = buttonListModifyOnClickListener;
    }

    //获取列表项的个数
    @Override
    public int getCount() {
        return workerList.size();
    }

    //获取列表项的数据
    @Override
    public Object getItem(int position) {
        return workerList.get(position);
    }

    //获取列表项的编号
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获取指定位置的列表视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkerBaseAdapter3.ViewHolder viewHolder;
        //如果转换视图为空
        if(convertView==null)
        {
            viewHolder=new WorkerBaseAdapter3.ViewHolder();//创建一个新的视图持有者
            //根据布局文件worker_listview_delete.xml生成转换视图对象
            convertView= LayoutInflater.from(context).inflate(R.layout.worker_listview_modify,null);
            viewHolder.textView_query_id3=convertView.findViewById(R.id.textview_query_id3);
            viewHolder.textView_query_name3=convertView.findViewById(R.id.textview_query_name3);
            viewHolder.textView_query_sex3=convertView.findViewById(R.id.textview_query_sex3);
            viewHolder.textView_query_department3=convertView.findViewById(R.id.textview_query_department3);
            viewHolder.textView_query_position3=convertView.findViewById(R.id.textview_query_position3);
            viewHolder.textView_query_salary3=convertView.findViewById(R.id.textview_query_salary3);
            viewHolder.textView_query_phone3=convertView.findViewById(R.id.textview_query_phone3);
            viewHolder.button_list_modify=convertView.findViewById(R.id.button_list_modify);
            convertView.setTag(viewHolder);//将视图持有者保存到转换视图当中
        }else
        {
            //如果转换视图非空，则从转换视图中获取之前保存的视图持有者
            viewHolder=(WorkerBaseAdapter3.ViewHolder) convertView.getTag();
        }
        Worker worker=workerList.get(position);
        viewHolder.textView_query_id3.setText(worker.getId());//显示职工编号
        viewHolder.textView_query_name3.setText(worker.getName());//显示职工姓名
        viewHolder.textView_query_sex3.setText(worker.getSex());//显示职工性别
        viewHolder.textView_query_department3.setText(worker.getDepartment());//显示职工部门
        viewHolder.textView_query_position3.setText(worker.getPosition());//显示职工职位
        viewHolder.textView_query_salary3.setText(worker.getSalary());//显示职工工资
        viewHolder.textView_query_phone3.setText(worker.getPhone());//显示职工电话
        viewHolder.button_list_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListModifyOnClickListener.buttonListModifyOnClickListener(v,position);
            }
        });
        return convertView;
    }
    //定义一个视图持有者，以便重用列表项的视图资源
    public final class ViewHolder
    {
        public TextView textView_query_id3;//声明职工编号的文本视图对象
        public TextView textView_query_name3;//声明职工姓名的文本视图对象
        public TextView textView_query_sex3;//声明职工性别的文本视图对象
        public TextView textView_query_department3;//声明职工部门的文本视图对象
        public TextView textView_query_position3;//声明职工职位的文本视图对象
        public TextView textView_query_salary3;//声明职工工资的文本视图对象
        public TextView textView_query_phone3;//声明职工电话的文本视图对象
        private Button button_list_modify;//声明删除职工按钮
    }
}
