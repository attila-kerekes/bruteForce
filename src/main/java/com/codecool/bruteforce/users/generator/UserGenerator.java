package com.codecool.bruteforce.users.generator;

import com.codecool.bruteforce.users.model.User;

import java.util.List;

public interface UserGenerator {
    List<User> generate(int count, int maxPasswordLength);
}
