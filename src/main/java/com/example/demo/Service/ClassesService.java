package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Mapper.ClassesMapper;
import com.example.demo.Model.ClassesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {
    private final ClassesMapper dao;
    @Autowired
    public ClassesService(ClassesMapper dao){
        this.dao = dao;
    }
    public boolean insert(ClassesModel model) {
        return dao.insert(model) > 0;
    }

    public ClassesModel select(int id) {
        return dao.select(id);
    }

    public List<ClassesModel> selectAll() {
        return dao.selectAll();
    }

    public boolean updateValue(ClassesModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
