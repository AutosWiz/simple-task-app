package com.example.task.application.user.update;

import lombok.Data;

@Data
public class UserUpdateCommand {
    private final Integer id;
    private final String username;
}
