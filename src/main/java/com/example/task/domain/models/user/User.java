package com.example.task.domain.models.user;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class User {
    private final UserId id;
    private Username name;
    private EncryptedPassword encryptedPassword;

    public User(UserId id, Username name, EncryptedPassword encryptedPassword) {
        if (id == null) throw new NullPointerException();
        if (name == null) throw new NullPointerException();
        if (encryptedPassword == null) throw new NullPointerException();

        this.id = id;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
    }

    public void changeName(Username name) {
        if (name == null) throw new NullPointerException();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
