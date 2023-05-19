package com.example.workermanagesystem;

import java.io.Serializable;

public class Worker implements Serializable {
    private String id;//职工编号
    private String name;//职工姓名
    private String sex;//职工性别
    private String department;//职工所在部门
    private String position;//职工职位
    private String salary;//职工工资
    private String phone;//职工电话

    public Worker(){}

    public Worker(String id,String name,String sex,String department,String position,String salary,String phone)
    {
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.department=department;
        this.position=position;
        this.salary=salary;
        this.phone=phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getSalary() {
        return salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
