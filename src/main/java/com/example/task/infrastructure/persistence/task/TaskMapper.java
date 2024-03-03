package com.example.task.infrastructure.persistence.task;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {

    @Update("UPDATE tasks_sequence SET id = LAST_INSERT_ID(id + 1)")
    boolean updateSequence();

    @Select("SELECT LAST_INSERT_ID()")
    int selectSequence();

    @Select("SELECT * FROM tasks")
    List<TaskDataModel> selectAll();

    @Select("SELECT * FROM tasks WHERE user_id = #{userId}")
    List<TaskDataModel> selectByUserId(Integer userId);

    @Select("SELECT * FROM tasks WHERE id = #{taskId}")
    Optional<TaskDataModel> selectById(Integer id);

    @Insert({"""
            INSERT INTO tasks(id, user_id, title, description, due_date, task_status)
            VALUES(#{taskId}, #{userId}, #{taskTitle}, #{taskDescription}, #{dueDate}, #{taskStatus})
            """})
    boolean insert(TaskDataModel taskDataModel);

    @Update({"""
            UPDATE tasks SET title = #{taskTitle},
            description = #{taskDescription},
            task_status = #{taskStatus},
            due_date = #{dueDate}
            WHERE id = #{taskId}
            """})
    boolean update(TaskDataModel taskDataModel);

    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
    boolean delete(TaskDataModel taskDataModel);
}
