package com.codecool.bruteforce.users.repository;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.users.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String dbFile;
    private Logger logger;

    public UserRepositoryImpl(String dbFile, Logger logger) {
        this.dbFile = dbFile;
        this.logger = logger;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbFile;
            conn = DriverManager.getConnection(url);

            //logger.logInfo("Connection to SQLite has been established.");

            return conn;

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return null;
    }

    public void add(String userName, String password) {
        String sql = "INSERT INTO users(user_name, password) VALUES(?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    logger.logInfo("User added successfully with ID: " + id);
                }
            }


        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }
    }

    public void update(int id, String userName, String password) {
    }

    public void delete(int id) {
    }

    public void deleteAll() {
    }

    public User get(int id) {
        return null;
    }

    public List<User> getAll() {
        String sql = "SELECT id, user_name, password FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");

                // Create User object and add to the list
                User user = new User(id, userName, password);
                users.add(user);
            }

            //logger.logInfo("Retrieved all users.");

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return users;
    }
}
