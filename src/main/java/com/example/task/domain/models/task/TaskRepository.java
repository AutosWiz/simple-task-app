package com.example.task.domain.models.task;

import com.example.task.domain.models.user.UserId;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId id);
    List<Task> findAll();
    List<Task> findByUserId(UserId userId);
    void save(Task task);
    void delete(Task task);
}
