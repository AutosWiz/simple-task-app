package com.example.task.presentation.constorllers.user;

import com.example.task.presentation.validation.Confirm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Confirm(field = "password", confirmField = "confirmPassword")
public class UserCreateForm implements Serializable {

    @NotNull
    @Size(min = 5, max = 20)
    public String username;

    @NotNull
    @Size(min = 6)
    public String password;

    @NotNull
    @Size(min = 6)
    public String confirmPassword;
}
