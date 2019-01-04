package com.example.demo.Model;

/**
 * Created by ZHW on 2018/12/26.
 */

public class TeachersModel {

    private long Id;
    private String Name;
    private String Password;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
