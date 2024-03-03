package com.example.task.application.task.transition;

import com.example.task.domain.models.task.TaskStatus;
import lombok.Data;

@Data
public class TaskTransitionCommand {
    private final Integer taskId;
    private final Integer userId;
    private final TaskStatus status;
}
