package com.example.task.domain.models.task;

import com.example.task.domain.models.user.UserId;

import java.time.LocalDate;

public interface TaskFactory {
    Task create(TaskTitle title, TaskDescription description, LocalDate dueDate, UserId userId);
}
