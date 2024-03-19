package com.codecool.bruteforce.users.generator;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.passwords.generator.PasswordGenerator;
import com.codecool.bruteforce.users.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserGeneratorImpl implements UserGenerator {
    private Logger logger;
    private final List<PasswordGenerator> passwordGenerators;

    private int userCount;

    public UserGeneratorImpl(Logger logger, List<PasswordGenerator> passwordGenerators) {
        this.logger = logger;
        this.passwordGenerators = passwordGenerators;
    }

    @Override
    public List<User> generate(int count, int maxPasswordLength) {
        List<User> users = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String userName = "user" + i;
            PasswordGenerator passwordGenerator = getRandomPasswordGenerator();
            String password = generateRandomPassword(maxPasswordLength, passwordGenerator);
            users.add(new User(-i, userName, password));
        }
        return users;
    }

    private PasswordGenerator getRandomPasswordGenerator() {
        int randomIndex = ThreadLocalRandom.current().nextInt(passwordGenerators.size());
        return passwordGenerators.get(randomIndex);
    }

    private String generateRandomPassword(int maxPasswordLength, PasswordGenerator passwordGenerator) {
        int passwordLength = ThreadLocalRandom.current().nextInt(1, maxPasswordLength + 1);
        return passwordGenerator.generate(passwordLength);
    }
}