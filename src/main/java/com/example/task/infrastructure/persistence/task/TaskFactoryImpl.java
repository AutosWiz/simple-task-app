package com.example.task.infrastructure.persistence.task;

import com.example.task.domain.models.task.*;
import com.example.task.domain.models.user.UserId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskFactoryImpl implements TaskFactory {
    private final TaskMapper taskMapper;

    public TaskFactoryImpl(final TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Task create(TaskTitle title, TaskDescription description, LocalDate dueDate, UserId userId) {
        boolean updated = taskMapper.updateSequence();
        if (!updated) throw new RuntimeException();
        int sequence = taskMapper.selectSequence();

        TaskId taskId = new TaskId(sequence);

        return new Task(
                taskId,
                userId,
                title,
                description,
                dueDate,
                TaskStatus.NEW
        );
    }
}
