package com.example.demo.Model;

import com.example.demo.Service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ZHW on 2018/12/26.
 */

public class StudentsModel {


    private long Id;
    private String Name;
    private String Sno;
    private String Ip;
    private long Class_id;

    public long getClass_id() {
        return Class_id;
    }

    public void setClass_id(long class_id) {
        Class_id = class_id;
    }

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

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

}
