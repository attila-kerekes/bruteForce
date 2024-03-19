package com.codecool.bruteforce.authentication;

import com.codecool.bruteforce.users.model.User;
import com.codecool.bruteforce.users.repository.UserRepository;

import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String username, String password) {
        List<User> users = userRepository.getAll();

        for (User user : users) {
            if (user.userName().equals(username) && user.password().equals(password)) {
                return true;
            }
        }

        return false;
    }

}
