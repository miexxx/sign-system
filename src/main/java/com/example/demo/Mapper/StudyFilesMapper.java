package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.StudentSignModel;
import com.example.demo.Model.StudyFilesModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudyFilesMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO study_files(path, file_name, class_id) VALUES(#{path}, #{file_name}, #{class_id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'study_files')", before = false, keyProperty = "id", resultType = int.class)
    int insert(StudyFilesModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM study_files WHERE user_id=#{id}")
    StudyFilesModel select(int id);

    // 查询全部
    @Select("SELECT * FROM study_files WHERE class_id=#{class_id}")
    List<StudyFilesModel> selectAll(int class_id);

    // 更新 value
    @Update("UPDATE study_files SET value=#{value} WHERE id=#{id}")
    int updateValue(StudyFilesModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM study_files WHERE id=#{id}")
    int delete(Integer id);
}
