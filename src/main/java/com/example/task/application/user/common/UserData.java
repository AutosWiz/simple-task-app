package com.example.task.application.user.common;

import com.example.task.domain.models.user.User;
import lombok.Data;

@Data
public class UserData {
    private final Integer id;
    private final String username;

    public static UserData toData(User source) {
        return new UserData(
                source.getId().getValue(),
                source.getName().getValue()
        );
    }
}
