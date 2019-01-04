package com.example.demo.Resource;

import com.example.demo.Model.StudentSignModel;
import com.example.demo.Model.StudentsModel;

import java.text.SimpleDateFormat;

/**
 * Created by ZHW on 2018/12/27.
 */
public class StudentSignInfo {
    private StudentSignModel studentSignModel;
    private StudentsModel studentsModel;

    public StudentSignModel getStudentSignModel() {
        return studentSignModel;
    }

    public void setStudentSignModel(StudentSignModel studentSignModel) {
        this.studentSignModel = studentSignModel;
    }

    public StudentsModel getStudentsModel() {
        return studentsModel;
    }

    public void setStudentsModel(StudentsModel studentsModel) {
        this.studentsModel = studentsModel;
    }

    public String getSignTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(this.studentSignModel.getSign_time());
    }
}
