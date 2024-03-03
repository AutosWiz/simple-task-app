package com.example.task.application.login;

import com.example.task.domain.models.user.UserRepository;
import com.example.task.domain.models.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var name = new Username(username);
        var user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(name.getValue()));

        return new LoginUser(
                user.getName().getValue(),
                user.getEncryptedPassword().getValue(),
                new ArrayList<>(),
                user.getId().getValue()
        );
    }
}
