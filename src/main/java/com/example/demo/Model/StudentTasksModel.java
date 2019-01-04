package com.example.demo.Model;

/**
 * Created by ZHW on 2018/12/27.
 */
public class StudentTasksModel {
   private long Id;
   private String Path;
   private String File_name;
   private int Task_id;
   private int User_id;

    public String getFile_name() {
        return File_name;
    }

    public void setFile_name(String file_name) {
        File_name = file_name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }



    public int getTask_id() {
        return Task_id;
    }

    public void setTask_id(int task_id) {
        Task_id = task_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }
}
