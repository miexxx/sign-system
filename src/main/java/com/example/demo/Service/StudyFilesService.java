package com.example.demo.Service;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Mapper.StudentSignMapper;
import com.example.demo.Mapper.StudyFilesMapper;
import com.example.demo.Model.StudentSignModel;
import com.example.demo.Model.StudyFilesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyFilesService {
    private final StudyFilesMapper dao;
    @Autowired
    public StudyFilesService(StudyFilesMapper dao){
        this.dao = dao;
    }
    public boolean insert(StudyFilesModel model) {
        return dao.insert(model) > 0;
    }

    public StudyFilesModel select(int id) {
        return dao.select(id);
    }

    public List<StudyFilesModel> selectAll(int class_id) {
        return dao.selectAll(class_id);
    }

    public boolean updateValue(StudyFilesModel model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(Integer id) {
        return dao.delete(id) > 0;
    }
}
