package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Mapper.ClassesMapper;
import com.example.demo.Mapper.TasksMapper;
import com.example.demo.Model.ClassesModel;
import com.example.demo.Model.TasksModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    private final TasksMapper dao;
    @Autowired
    public TasksService(TasksMapper dao){
        this.dao = dao;
    }
    public boolean insert(TasksModel model) {
        return dao.insert(model) > 0;
    }

    public TasksModel select(int id) {
        return dao.select(id);
    }

    public List<TasksModel> selectAll(int class_id) {
        return dao.selectAll(class_id);
    }

    public boolean updateValue(TasksModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
