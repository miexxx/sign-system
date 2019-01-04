package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Mapper.StudentSignMapper;
import com.example.demo.Model.StudentSignModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSignService {
    private final StudentSignMapper dao;
    @Autowired
    public StudentSignService(StudentSignMapper dao){
        this.dao = dao;
    }
    public boolean insert(StudentSignModel model) {
        return dao.insert(model) > 0;
    }

    public StudentSignModel select(int user_id) {
        return dao.select(user_id);
    }

    public List<StudentSignModel> selectAll(int class_id) {
        return dao.selectAll(class_id);
    }

    public boolean updateValue(StudentSignModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete() {
        return dao.delete() > 0;
    }
}
