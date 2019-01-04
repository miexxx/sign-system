package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.ClassesModel;
import com.example.demo.Model.StudentsModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ClassesMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO classes(name) VALUES(#{name})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'classes')", before = false, keyProperty = "id", resultType = int.class)
    int insert(ClassesModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM classes WHERE id=#{id}")
    ClassesModel select(int id);

    // 查询全部
    @Select("SELECT * FROM classes")
    List<ClassesModel> selectAll();

    // 更新 value
    @Update("UPDATE classes SET value=#{value} WHERE id=#{id}")
    int updateValue(ClassesModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM classes WHERE id=#{id}")
    int delete(Integer id);
}
