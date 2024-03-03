package com.example.task.application.task.update;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskUpdateCommand {
    private final Integer taskId;
    private final Integer userId;
    private final String title;
    private final String description;
    private final LocalDate dueDate;
}
