package com.example.task.domain.models.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Username {
    private final String value;

    private static final int MIN_USERNAME_LENGTH = 5;
    private static final int MAX_USERNAME_LENGTH = 30;

    public Username(String value) {
        if (value == null) throw new NullPointerException();
        if (value.length() < MIN_USERNAME_LENGTH) throw new IllegalArgumentException("ユーザー名は5文字以上です。");
        if (value.length() > MAX_USERNAME_LENGTH) throw new IllegalArgumentException("ユーザー名は30文字以内です。");

        this.value = value;
    }
}
