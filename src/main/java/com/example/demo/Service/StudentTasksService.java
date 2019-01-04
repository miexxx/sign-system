package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Mapper.StudentTasksMapper;
import com.example.demo.Model.StudentTasksModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTasksService {
    private final StudentTasksMapper dao;
    @Autowired
    public StudentTasksService(StudentTasksMapper dao){
        this.dao = dao;
    }
    public boolean insert(StudentTasksModel model) {
        return dao.insert(model) > 0;
    }

    public StudentTasksModel select(int user_id,int task_id) {
        return dao.select(user_id, task_id);
    }

    public List<StudentTasksModel> selectAll(int user_id) {
        return dao.selectAll(user_id);
    }

    public boolean updateValue(StudentTasksModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
