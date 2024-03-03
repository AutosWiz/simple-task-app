package com.example.task.application.task.getbyuserId;

import com.example.task.application.task.common.TaskData;
import lombok.Data;

import java.util.List;

@Data
public class TaskGetByUserIdResult {
    private final List<TaskData> tasks;
}
