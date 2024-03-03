package com.example.task.domain.models.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId id);
    Optional<User> findByUsername(Username username);
    List<User> findAll();
    void save(User user);
    void delete(User user);
}
