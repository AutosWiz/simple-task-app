package com.example.task.domain.models.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class TaskDescription {
    private final String value;
    private final static int MAX_TASK_DESCRIPTION_LENGTH = 1000;

    public TaskDescription(String value) {
        if (value == null) throw new NullPointerException();
        if (value.length() > MAX_TASK_DESCRIPTION_LENGTH) throw new IllegalArgumentException("説明は1000文字以内で入力してください。");

        this.value = value;
    }
}
