package com.example.demo.Model;

/**
 * Created by ZHW on 2018/12/27.
 */
public class StudyFilesModel {
    private long Id;
    private String Path;
    private String File_name;
    private int Class_id;

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

    public String getFile_name() {
        return File_name;
    }

    public void setFile_name(String file_name) {
        File_name = file_name;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }
}
