package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */
import com.example.demo.Mapper.StudentsMapper;
import com.example.demo.Model.StudentsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private final StudentsMapper dao;
    @Autowired
    public StudentsService(StudentsMapper dao){
        this.dao = dao;
    }
    public boolean insert(StudentsModel model) {
        return dao.insert(model) > 0;
    }

    public StudentsModel select(int sno) {
        return dao.select(sno);
    }

    public StudentsModel selectId(int id) {
        return dao.selectId(id);
    }

    public List<StudentsModel> selectAll(int class_id) {
        return dao.selectAll(class_id);
    }

    public boolean updateValue(StudentsModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
