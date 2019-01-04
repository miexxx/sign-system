package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.ClassesModel;
import com.example.demo.Model.TasksModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TasksMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO tasks(title,publisher,class_id) VALUES(#{title},#{publisher},#{class_id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'tasks')", before = false, keyProperty = "id", resultType = int.class)
    int insert(TasksModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM tasks WHERE id=#{id}")
    TasksModel select(int id);

    // 查询全部
    @Select("SELECT * FROM tasks WHERE class_id=#{class_id}")
    List<TasksModel> selectAll(int class_id);

    // 更新 value
    @Update("UPDATE tasks SET value=#{value} WHERE id=#{id}")
    int updateValue(TasksModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM tasks WHERE id=#{id}")
    int delete(Integer id);
}
