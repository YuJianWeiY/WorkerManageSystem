package com.example.workermanagesystem;

import java.util.ArrayList;
import java.util.List;

public class WorkerDao extends JDBCUtils2{
    public Worker findID(String id)
    {
        connect2();//获得连接
        Worker worker=null;
        try{
            //处理sql语句。我这里是根据我自己的worker表的id字段来查询记录
            String sql="select * from worker where id=?";
            //获得预处理对象。获取用于向数据库发送sql语句的preparedStatement
            preparedStatement=connection.prepareStatement(sql);
            //设置实际参数。用于根据编号进行查询
            preparedStatement.setString(1,id);
            //执行。执行sql查询语句并返回结果集
            resultSet= preparedStatement.executeQuery();
            //如果当前记录不是结果集中的最后一行，则进入循环体。
            while (resultSet.next())
            {
                worker=new Worker();
                //获取id列列值
                worker.setId(resultSet.getString("id"));
            }
        }catch (Exception e){
            //对异常情况进行处理
            e.printStackTrace();
        }finally {
            close2();
        }return worker;
    }
    public int addWorker(Worker worker)
    {
        int value=0;
        connect2();
        try {
            //处理sql语句。我这里是向我自己worker表的id,name,sex,department,position,salary,phone添加数据
            String sql="insert into worker(id,name,sex,department,position,salary,phone) values(?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            //设置实际参数。将数据插入数据库中
            preparedStatement.setString(1, worker.getId());//设置编号
            preparedStatement.setString(2, worker.getName());//设置姓名
            preparedStatement.setString(3, worker.getSex());//设置性别
            preparedStatement.setString(4, worker.getDepartment());//设置部门
            preparedStatement.setString(5, worker.getPosition());//设置职位
            preparedStatement.setString(6, worker.getSalary());//设置工资
            preparedStatement.setString(7, worker.getPhone());//设置电话号码
            value=preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close2();
        }return value;
    }
    public List<Worker> findWorkerList()
    {
        List<Worker> list=new ArrayList<>();
        connect2();
        try{
            String sql="select * from worker";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Worker worker=new Worker();
                worker.setId(resultSet.getString("id"));
                worker.setName(resultSet.getString("name"));
                worker.setSex(resultSet.getString("sex"));
                worker.setDepartment(resultSet.getString("department"));
                worker.setPosition(resultSet.getString("position"));
                worker.setSalary(resultSet.getString("salary"));
                worker.setPhone(resultSet.getString("phone"));
                list.add(worker);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close2();
        }return list;
    }
    public int deleteWorker(String id)
    {
        int value=0;
        try {
            connect2();
            String sql="delete from worker where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            value=preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close2();
        }return value;
    }
    public int modifyWorker(Worker worker)
    {
        int value=0;
        connect2();
        try {
            String sql="update worker set name=?,sex=?,department=?,position=?,salary=?,phone=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,worker.getName());
            preparedStatement.setString(2,worker.getSex());
            preparedStatement.setString(3,worker.getDepartment());
            preparedStatement.setString(4,worker.getPosition());
            preparedStatement.setString(5,worker.getSalary());
            preparedStatement.setString(6,worker.getPhone());
            preparedStatement.setString(7,worker.getId());
            value=preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close2();
        }return value;
    }
    public Worker findID2(String id)
    {
        connect2();//获得连接
        Worker worker=null;
        try{
            //处理sql语句。我这里是根据我自己的worker表的id字段来查询记录
            String sql="select * from worker where id=?";
            //获得预处理对象。获取用于向数据库发送sql语句的preparedStatement
            preparedStatement=connection.prepareStatement(sql);
            //设置实际参数。用于根据编号进行查询
            preparedStatement.setString(1,id);
            //执行。执行sql查询语句并返回结果集
            resultSet= preparedStatement.executeQuery();
            //如果当前记录不是结果集中的最后一行，则进入循环体。
            while (resultSet.next())
            {
                String name=resultSet.getString("name");
                String sex=resultSet.getString("sex");
                String department=resultSet.getString("department");
                String position=resultSet.getString("position");
                String salary=resultSet.getString("salary");
                String phone=resultSet.getString("phone");
                worker=new Worker(id,name,sex,department,position,salary,phone);
            }
        }catch (Exception e){
            //对异常情况进行处理
            e.printStackTrace();
        }finally {
            close2();
        }return worker;
    }
}
