package com.codecool.bruteforce.users.repository;

import com.codecool.bruteforce.users.model.User;

import java.util.List;

public interface UserRepository {
    void add(String userName, String password);
    void update(int id, String userName, String password);
    void delete(int id);
    void deleteAll();

    User get(int id);
    List<User> getAll();
}
