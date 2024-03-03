package com.example.task.application.task.getall;

import com.example.task.application.task.common.TaskData;
import lombok.Data;

import java.util.List;

@Data
public class TaskGetAllResult {
    private final List<TaskData> tasks;
}
