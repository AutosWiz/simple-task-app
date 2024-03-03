package com.example.task.infrastructure.persistence.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDataModel {
    public Integer id;
    public String username;
    public String password;
}
