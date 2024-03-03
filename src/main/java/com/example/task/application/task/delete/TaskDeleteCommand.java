package com.example.task.application.task.delete;

import lombok.Data;

@Data
public class TaskDeleteCommand {
    private final Integer taskId;
    private final Integer userId;
}
