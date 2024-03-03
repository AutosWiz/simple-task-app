package com.example.task.infrastructure.persistence.task;

import com.example.task.domain.models.task.*;
import com.example.task.domain.models.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskMapper taskMapper;

    @Override
    public Optional<Task> findById(TaskId id) {
        var found = taskMapper.selectById(id.getValue());
        return found.map(this::toModel);
    }

    @Override
    public List<Task> findAll() {
        var taskDataModels = taskMapper.selectAll();
        return taskDataModels.stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public List<Task> findByUserId(UserId userId) {
        var taskDataModels = taskMapper.selectByUserId(userId.getValue());
        return taskDataModels.stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public void save(Task task) {
        var found = taskMapper.selectById(task.getTaskId().getValue());
        var dataModel = toDataModel(task);
        found.ifPresentOrElse(data -> {
            taskMapper.update(dataModel);
        }, () -> {
            taskMapper.insert(dataModel);
        });
    }

    @Override
    public void delete(Task task) {
        var dataModel = toDataModel(task);
        taskMapper.delete(dataModel);
    }

    private Task toModel(TaskDataModel from) {
        return new Task(
                new TaskId(from.getTaskId()),
                new UserId(from.getUserId()),
                new TaskTitle(from.getTaskTitle()),
                new TaskDescription(from.getTaskDescription()),
                from.getDueDate(),
                from.getTaskStatus()
        );
    }

    private TaskDataModel toDataModel(Task from) {
        return new TaskDataModel(
                from.getTaskId().getValue(),
                from.getUserId().getValue(),
                from.getTaskTitle().getValue(),
                from.getTaskDescription().getValue(),
                from.getDueDate(),
                from.getTaskStatus()
        );
    }
}
