package com.example.task.application.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoginUser extends User {
    private final int userId;

    public int getUserId() {
        return userId;
    }

    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, int userId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }
}
