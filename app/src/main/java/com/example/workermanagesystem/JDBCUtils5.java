package com.example.workermanagesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtils5 {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static int getWorkerWoman()
    {
        //要连接MySQL数据库的URL    URL_MySQL="jdbc:mysql://外网地址:端口/数据库名称"
        final String URL_MySQL="jdbc:mysql://外网地址:端口/数据库名称";
        //要连接MySQL数据库的用户名  NAME_MySQL="MySQL用户名"
        final String NAME_MySQL="MySQL用户名";
        //要连接MySQL数据库的密码    PASSWORD_MySQL="MySQL密码"
        final String PASSWORD_MySQL="MySQL密码";
        connection=null;
        int count=0;
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //获取与数据库的连接
            connection= DriverManager.getConnection(URL_MySQL,NAME_MySQL,PASSWORD_MySQL);
            String sql="SELECT COUNT(*) FROM worker WHERE sex = '女'";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next())
            {
                count=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
}
