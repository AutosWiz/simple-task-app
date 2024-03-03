package com.example.task.application.user.register;

import lombok.Data;

@Data
public class UserRegisterCommand {
    private final String username;
    private final String password;
}
