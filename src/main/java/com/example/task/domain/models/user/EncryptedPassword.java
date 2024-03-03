package com.example.task.domain.models.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class EncryptedPassword {
    private final String value;

    public EncryptedPassword(String value) {
        this.value = value;
    }
}
