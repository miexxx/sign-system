package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Mapper.StudentsMapper;
import com.example.demo.Mapper.TeachersMapper;
import com.example.demo.Model.StudentsModel;
import com.example.demo.Model.TeachersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachersService {
    private final TeachersMapper dao;
    @Autowired
    public TeachersService(TeachersMapper dao){
        this.dao = dao;
    }
    public boolean insert(TeachersModel model) {
        return dao.insert(model) > 0;
    }

    public TeachersModel select(String name) {
        return dao.select(name);
    }

    public boolean updateValue(TeachersModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
