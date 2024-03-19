package com.codecool.bruteforce.passwords.breaker;

import java.util.ArrayList;
import java.util.List;

public class PasswordBreakerImpl implements PasswordBreaker {

    @Override
    public List<String> getCombinations(int passwordLength) {
        List<List<String>> characters = new ArrayList<>();
        for (int i = 0; i < passwordLength; i++) {
            characters.add(getAsciiCharacters());
        }
        return getAllPossibleCombos(characters);
    }

    private static List<String> getAsciiCharacters() {
        List<String> asciiChars = new ArrayList<>();
        for (int i = 0; i < 127; i++) {
            asciiChars.add(String.valueOf((char) i));
        }
        return asciiChars;
    }

    private static List<String> getAllPossibleCombos(List<List<String>> strings) {
        List<String> combos = new ArrayList<>(List.of(""));

        combos = strings
                .stream()
                .reduce(combos, (current, inner) -> current.stream()
                        .flatMap(c -> inner.stream().map(c::concat))
                        .toList(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });

        return combos;
    }
}
