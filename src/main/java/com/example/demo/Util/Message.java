package com.example.demo.Util;

import com.example.demo.Resource.Swal;
import com.example.demo.Service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ZHW on 2018/12/26.
 */
public class Message {
    public static Swal SwalMessage(String message, Integer status){
        Swal swal = new Swal(message,status);
        return swal;
    }
}
