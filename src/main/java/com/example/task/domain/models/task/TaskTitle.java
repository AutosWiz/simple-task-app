package com.example.task.domain.models.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class TaskTitle {
    private final String value;
    public static final int MAX_TASK_TITLE_LENGTH = 100;

    public TaskTitle(String value) {
        if (value == null) throw new NullPointerException();
        if (value.length() > 100) throw new IllegalArgumentException("タスクタイトルは100文字以内で入力してください。");

        this.value = value;
    }
}
