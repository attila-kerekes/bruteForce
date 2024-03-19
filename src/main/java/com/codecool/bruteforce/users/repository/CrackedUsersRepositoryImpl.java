package com.codecool.bruteforce.users.repository;

import com.codecool.bruteforce.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrackedUsersRepositoryImpl implements CrackedUsersRepository {
    private final String dbFile;
    private Logger logger;

    public CrackedUsersRepositoryImpl(String dbFile, Logger logger) {
        this.dbFile = dbFile;
        this.logger = logger;
    }

    @Override
    public void addCrackedUser(String userName, String crackedPassword, long timeTaken) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile)) {
            String sql = "INSERT INTO cracked_users (user_name, cracked_password, time_taken) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, crackedPassword);
                pstmt.setLong(3, timeTaken);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.logError("Error while adding cracked user to CrackedUsers database: " + e.getMessage());
        }
    }

    // Implement other necessary methods here for interacting with the CrackedUsers database
}
