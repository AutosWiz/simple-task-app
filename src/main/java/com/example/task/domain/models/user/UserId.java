package com.example.task.domain.models.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserId {
    private final Integer value;

    public UserId(Integer value) {
        if (value == null) throw new NullPointerException();

        this.value = value;
    }
}