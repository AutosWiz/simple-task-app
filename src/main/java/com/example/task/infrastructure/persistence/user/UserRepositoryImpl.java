package com.example.task.infrastructure.persistence.user;

import com.example.task.domain.models.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(UserId id) {
        var found = userMapper.selectById(id.getValue());
        return found.map(this::toModel);
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        var found = userMapper.selectByName(username.getValue());
        return found.map(this::toModel);
    }

    @Override
    public List<User> findAll() {
        var userDataModels = userMapper.selectAll();
        return userDataModels.stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public void save(User user) {
        var found = userMapper.selectById(user.getId().getValue());
        var dataModel = toDataModel(user);
        found.ifPresentOrElse(data -> {
            userMapper.update(dataModel);
        }, () -> {
            userMapper.insert(dataModel);
        });
    }

    @Override
    public void delete(User user) {
        var dataModel = toDataModel(user);
        userMapper.delete(dataModel);
    }

    private User toModel(UserDataModel from) {
        return new User(
                new UserId(from.getId()),
                new Username(from.getUsername()),
                new EncryptedPassword(from.getPassword())
        );
    }

    private UserDataModel toDataModel(User from) {
        return new UserDataModel(
                from.getId().getValue(),
                from.getName().getValue(),
                from.getEncryptedPassword().getValue()
        );
    }
}
