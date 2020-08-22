package com.codeup.eyearbook.services;

import com.codeup.eyearbook.models.User;
import com.codeup.eyearbook.models.UserWithRoles;
import com.codeup.eyearbook.repositories.Roles;
import com.codeup.eyearbook.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;
    private final Roles roles;

    public UserDetailsLoader(UserRepository users, Roles roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public UserWithRoles loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }


        return new UserWithRoles(user, roles.ofUserWith(username));
    }
}