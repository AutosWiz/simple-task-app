package com.example.task.application.task.common;

import com.example.task.domain.models.task.Task;
import com.example.task.domain.models.task.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskData {
    private final Integer taskId;
    private final Integer userId;
    private final String taskTitle;
    private final String taskDescription;
    private final LocalDate dueDate;
    private final TaskStatus taskStatus;

    public static TaskData toData(Task source) {
        return new TaskData(
                source.getTaskId().getValue(),
                source.getUserId().getValue(),
                source.getTaskTitle().getValue(),
                source.getTaskDescription().getValue(),
                source.getDueDate(),
                source.getTaskStatus()
        );
    }
}
