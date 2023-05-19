package com.example.workermanagesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class WorkerBaseAdapter extends BaseAdapter {
    private Context context;//声明一个上下文对象
    private List<Worker> workerList;//声明一个职工信息列表
    public WorkerBaseAdapter(){}
    //职工适配器的构造方法，传入上下文与职工列表
    public WorkerBaseAdapter(Context context,List<Worker> workerList)
    {
        this.context=context;
        this.workerList=workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
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
        ViewHolder viewHolder;
        //如果转换视图为空
        if(convertView==null)
        {
            viewHolder=new ViewHolder();//创建一个新的视图持有者
            //根据布局文件worker_listview_query.xml生成转换视图对象
            convertView=LayoutInflater.from(context).inflate(R.layout.worker_listview_query,null);
            viewHolder.textView_query_id=convertView.findViewById(R.id.textview_query_id);
            viewHolder.textView_query_name=convertView.findViewById(R.id.textview_query_name);
            viewHolder.textView_query_sex=convertView.findViewById(R.id.textview_query_sex);
            viewHolder.textView_query_department=convertView.findViewById(R.id.textview_query_department);
            viewHolder.textView_query_position=convertView.findViewById(R.id.textview_query_position);
            viewHolder.textView_query_salary=convertView.findViewById(R.id.textview_query_salary);
            viewHolder.textView_query_phone=convertView.findViewById(R.id.textview_query_phone);
            convertView.setTag(viewHolder);//将视图持有者保存到转换视图当中
        }else
        {
            //如果转换视图非空，则从转换视图中获取之前保存的视图持有者
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Worker worker=workerList.get(position);
        viewHolder.textView_query_id.setText(worker.getId());//显示职工编号
        viewHolder.textView_query_name.setText(worker.getName());//显示职工姓名
        viewHolder.textView_query_sex.setText(worker.getSex());//显示职工性别
        viewHolder.textView_query_department.setText(worker.getDepartment());//显示职工部门
        viewHolder.textView_query_position.setText(worker.getPosition());//显示职工职位
        viewHolder.textView_query_salary.setText(worker.getSalary());//显示职工工资
        viewHolder.textView_query_phone.setText(worker.getPhone());//显示职工电话
        return convertView;
    }
    //定义一个视图持有者，以便重用列表项的视图资源
    public final class ViewHolder
    {
        public TextView textView_query_id;//声明职工编号的文本视图对象
        public TextView textView_query_name;//声明职工姓名的文本视图对象
        public TextView textView_query_sex;//声明职工性别的文本视图对象
        public TextView textView_query_department;//声明职工部门的文本视图对象
        public TextView textView_query_position;//声明职工职位的文本视图对象
        public TextView textView_query_salary;//声明职工工资的文本视图对象
        public TextView textView_query_phone;//声明职工电话的文本视图对象
    }
}
