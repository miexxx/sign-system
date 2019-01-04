package com.example.demo.Mapper;

/**
 * Created by ZHW on 2018/12/26.
 */

import com.example.demo.Model.StudentTasksModel;
import com.example.demo.Model.TasksModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentTasksMapper {
    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO student_tasks(path, file_name, task_id, user_id) VALUES(#{path}, #{file_name},#{task_id},#{user_id})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'student_tasks')", before = false, keyProperty = "id", resultType = int.class)
    int insert(StudentTasksModel model);

    // 根据 ID 查询
    @Select("SELECT * FROM student_tasks WHERE  user_id = #{user_id} and task_id = #{task_id}")
    StudentTasksModel select(@Param("user_id")int user_id,@Param("task_id")int task_id);

    // 查询全部
    @Select("SELECT * FROM student_tasks WHERE user_id = #{user_id}")
    List<StudentTasksModel> selectAll(int user_id);

    // 更新 value
    @Update("UPDATE student_tasks SET path=#{path},file_name=#{file_name} WHERE id=#{id}")
    int updateValue(StudentTasksModel model);

    // 根据 ID 删除
    @Delete("DELETE FROM student_tasks WHERE id=#{id}")
    int delete(Integer id);
}
