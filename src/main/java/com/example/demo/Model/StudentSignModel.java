package com.example.demo.Model;

import java.util.Date;

/**
 * Created by ZHW on 2018/12/27.
 */
public class StudentSignModel {
    private long Id;
    private Date Sign_time;
    private int Status;
    private int User_id;
    private int Class_id;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getSign_time() {
        return Sign_time;
    }

    public void setSign_time(Date sign_time) {
        Sign_time = sign_time;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }
}
