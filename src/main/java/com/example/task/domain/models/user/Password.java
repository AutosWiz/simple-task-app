package com.example.task.domain.models.user;

import lombok.Getter;

@Getter
public class Password {
    private final String value;

    private static final int MIN_PASSWORD_LENGTH = 6;
    public Password(String value) {
        if (value.length() < MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("パスワードは6文字以上で入力してください。");
        }
        this.value = value;
    }
}
