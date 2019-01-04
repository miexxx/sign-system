package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.StudentsModel;
import com.example.demo.Model.TeachersModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeachersMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO teachers(name, password) VALUES(#{name}, #{password})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'teachers')", before = false, keyProperty = "id", resultType = int.class)
    int insert(TeachersModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM teachers WHERE name=#{name}")
    TeachersModel select(String name);

    // 更新 value
    @Update("UPDATE teachers SET value=#{value} WHERE id=#{id}")
    int updateValue(TeachersModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM teachers WHERE id=#{id}")
    int delete(Integer id);
}
