package com.example.task.presentation.constorllers.task;

import com.example.task.domain.models.task.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskForm implements Serializable {
    private Integer taskId;

    @NotNull
    private String taskTitle;

    @NotNull
    private String taskDescription;

    @NotNull
    private LocalDate dueDate;

}
