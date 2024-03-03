package com.example.task.application.task.create;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskCreateCommand {
    private final String title;
    private final String description;
    private final Integer userId;
    private final LocalDate dueDate;
}
