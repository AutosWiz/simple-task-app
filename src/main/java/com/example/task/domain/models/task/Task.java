package com.example.task.domain.models.task;

import com.example.task.domain.models.user.UserId;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString
public class Task {
    private final TaskId taskId;
    private final UserId userId;
    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private LocalDate dueDate;
    private TaskStatus taskStatus;

    public Task(TaskId taskId, UserId userId, TaskTitle taskTitle, TaskDescription taskDescription, LocalDate dueDate, TaskStatus taskStatus) {
        if (taskId == null) throw new NullPointerException();
        if (userId == null) throw new NullPointerException();
        if (taskTitle == null) throw new NullPointerException();
        if (taskDescription == null) throw new NullPointerException();
        if (dueDate == null) throw new NullPointerException();
        if (taskStatus == null) throw new NullPointerException();

        this.taskId = taskId;
        this.userId = userId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    public void changeTitle(TaskTitle taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void changeDescription(TaskDescription description) {
        this.taskDescription = description;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void changeTaskStatus(TaskStatus to) {
        var taskStatusTransitions = new TaskStatusTransitions();
        boolean canTransition = taskStatusTransitions.canTransition(this.taskStatus, to);
        if (canTransition) {
            this.taskStatus = to;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}
