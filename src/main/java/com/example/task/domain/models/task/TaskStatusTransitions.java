package com.example.task.domain.models.task;

import java.util.*;

import static com.example.task.domain.models.task.TaskStatus.*;

public class TaskStatusTransitions {
    Map<TaskStatus, Set<TaskStatus>> allowed;

    {
        allowed = new HashMap<>();

        allowed.put(NEW, EnumSet.of(WORKING, WAITING, PENDING));

        allowed.put(WORKING, EnumSet.of(COMPLETED, DISCONTINUED, PENDING));

        allowed.put(WAITING, EnumSet.of(WORKING));

        allowed.put(DISCONTINUED, EnumSet.of(WORKING));

        allowed.put(PENDING, EnumSet.of(WORKING));
    }

    boolean canTransition(TaskStatus from, TaskStatus to) {
        Set<TaskStatus> allowedStatus = allowed.get(from);
        return allowedStatus.contains(to);
    }

    public Set<TaskStatus> getAllowedStatus(TaskStatus status) {
        return allowed.containsKey(status) ? allowed.get(status) : new HashSet<>();
    }
}
