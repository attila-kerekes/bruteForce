package com.codecool.bruteforce.passwords.generator;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.passwords.model.AsciiTableRange;

import java.util.Random;

public class PasswordGeneratorImpl implements PasswordGenerator {
    private static final Random random = new Random();
    private final AsciiTableRange[] characterSets;

    public PasswordGeneratorImpl(AsciiTableRange... characterSets) {
        this.characterSets = characterSets;
    }

    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            AsciiTableRange randomCharacterSet = getRandomCharacterSet();
            password.append(getRandomCharacter(randomCharacterSet));
        }

        return password.toString();
    }

    private AsciiTableRange getRandomCharacterSet() {
        int randomIndex = random.nextInt(characterSets.length);
        return characterSets[randomIndex];
    }

    private static char getRandomCharacter(AsciiTableRange characterSet) {
        int range = characterSet.end() - characterSet.start() + 1;
        int randomAscii = random.nextInt(range) + characterSet.start();
        return (char) randomAscii;
    }
}