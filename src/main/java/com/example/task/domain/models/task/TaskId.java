package com.example.task.domain.models.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class TaskId {
    private final Integer value;

    public TaskId(Integer value) {
        if (value == null) throw new NullPointerException();

        this.value = value;
    }
}
