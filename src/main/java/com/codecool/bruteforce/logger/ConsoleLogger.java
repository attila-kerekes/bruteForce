package com.codecool.bruteforce.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void logError(String message) {
        logMessage(message, "ERROR");
    }
    @Override
    public void logInfo(String message) {
        logMessage(message, "\u001b[32;1mINFO\u001b[0m");
    }

    private void logMessage(String message, String type) {
        String entry = "[" + LocalDateTime.now() + "] " + type + ": " + message;
        System.out.println(entry);
    }
}
