package com.example.task.infrastructure.persistence.user;

import com.example.task.domain.models.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory {
    private final UserMapper userMapper;

    @Override
    public User create(Username name, EncryptedPassword encryptedPassword) {
        boolean updated = userMapper.updateSequence();
        if (!updated) throw new RuntimeException();
        int sequence = userMapper.selectSequence();

        var userId = new UserId(sequence);

        return new User(userId, name, encryptedPassword);
    }
}
