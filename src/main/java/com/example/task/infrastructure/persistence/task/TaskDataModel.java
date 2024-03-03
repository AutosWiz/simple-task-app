package com.example.task.infrastructure.persistence.task;

import com.example.task.domain.models.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskDataModel {
    private final Integer taskId;
    private final Integer userId;
    private final String taskTitle;
    private final String taskDescription;
    private final LocalDate dueDate;
    private final TaskStatus taskStatus;
}
