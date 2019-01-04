package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface  StudentsMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO students(sno, name, ip, class_id) VALUES(#{sno}, #{name},#{ip},#{class_id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'students')", before = false, keyProperty = "id", resultType = int.class)
    int insert(StudentsModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM students WHERE sno=#{sno}")
    StudentsModel select(int sno);

    @Select("SELECT * FROM students WHERE id=#{id}")
    StudentsModel selectId(int id);

    // 查询全部
    @Select("SELECT * FROM students WHERE class_id=#{class_id}")
    List<StudentsModel> selectAll(int class_id);

    // 更新 value
    @Update("UPDATE students SET value=#{value} WHERE id=#{id}")
    int updateValue(StudentsModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM students WHERE id=#{id}")
    int delete(Integer id);
}
