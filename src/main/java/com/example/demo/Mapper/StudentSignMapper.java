package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.ClassesModel;
import com.example.demo.Model.StudentSignModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentSignMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO student_sign(sign_time, status, user_id, class_id) VALUES(#{sign_time}, #{status}, #{user_id}, #{class_id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'student_sign')", before = false, keyProperty = "id", resultType = int.class)
    int insert(StudentSignModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM student_sign WHERE user_id=#{user_id}")
    StudentSignModel select(int user_id);

    // 查询全部
    @Select("SELECT * FROM student_sign WHERE class_id=#{class_id}")
    List<StudentSignModel> selectAll(int class_id);

    // 更新 value
    @Update("UPDATE student_sign SET value=#{value} WHERE id=#{id}")
    int updateValue(StudentSignModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM student_sign")
    int delete();
}
