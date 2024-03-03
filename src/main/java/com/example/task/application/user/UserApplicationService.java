package com.example.task.application.user;

import com.example.task.application.user.common.UserData;
import com.example.task.application.user.delete.UserDeleteCommand;
import com.example.task.application.user.get.UserGetCommand;
import com.example.task.application.user.get.UserGetResult;
import com.example.task.application.user.getall.UserGetAllResult;
import com.example.task.application.user.register.UserRegisterCommand;
import com.example.task.application.user.register.UserRegisterResult;
import com.example.task.application.user.update.UserUpdateCommand;
import com.example.task.domain.models.user.*;
import com.example.task.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final PasswordEncoder passwordEncoder;
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserGetResult get(UserGetCommand command) {
        var id = new UserId(command.getId());
        var user = userRepository.findById(id);
        if (user.isEmpty()) throw new RuntimeException("ユーザーが見つかりませんでした。");

        var data = UserData.toData(user.get());

        return new UserGetResult(data);
    }

    public UserGetAllResult getAll() {
        var users = userRepository.findAll();
        var userDataList = users.stream()
                .map(UserData::toData)
                .toList();
        return new UserGetAllResult(userDataList);
    }

    @Transactional
    public UserRegisterResult register(UserRegisterCommand command) {
        var username = new Username(command.getUsername());
        var password = new Password(command.getPassword());
        var encryptedPassword = new EncryptedPassword(
                passwordEncoder.encode(password.getValue())
        );

        var user = userFactory.create(username, encryptedPassword);

        if (userService.exists(user)) throw new RuntimeException("ユーザーは既に存在しています。");

        userRepository.save(user);

        return new UserRegisterResult(user.getId().getValue());
    }

    @Transactional
    public void update(UserUpdateCommand command) {
        var id = new UserId(command.getId());
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("ユーザーが見つかりませんでした。"));

        if (command.getUsername() != null) {
            var name = new Username(command.getUsername());
            user.changeName(name);

            if (userService.exists(user)) throw new RuntimeException("ユーザーは既に存在しています。");
        }

        userRepository.save(user);
    }

    @Transactional
    public void delete(UserDeleteCommand command) {
        var id = new UserId(command.getId());
        var user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }
}
