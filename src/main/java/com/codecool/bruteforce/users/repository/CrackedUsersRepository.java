package com.codecool.bruteforce.users.repository;

public interface CrackedUsersRepository {
    void addCrackedUser(String userName, String crackedPassword, long timeTaken);
}

