package com.example.task.domain.services;

import com.example.task.domain.models.user.User;
import com.example.task.domain.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean exists(User user) {
        var duplicateUser = userRepository.findByUsername(user.getName());

        return duplicateUser.isPresent();
    }
}
