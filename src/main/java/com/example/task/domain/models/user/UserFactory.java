package com.example.task.domain.models.user;

public interface UserFactory {
    User create(Username name, EncryptedPassword encryptedPassword);
}
