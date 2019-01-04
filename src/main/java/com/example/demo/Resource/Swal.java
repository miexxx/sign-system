package com.example.demo.Resource;

/**
 * Created by ZHW on 2018/12/26.
 */
public class Swal {
    private String Message;
    private Integer Status;
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Swal(String message, Integer status){
        this.setMessage(message);
        this.setStatus(status);
    }
}
