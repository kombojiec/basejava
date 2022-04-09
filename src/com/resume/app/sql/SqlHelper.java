package com.resume.app.sql;


import com.resume.app.Config;
import com.resume.app.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private static final Config config = Config.getInstance();
    private static final String URL = config.getURL();
    private static final String USERNAME = config.getUsername();
    private static final String PASSWORD = config.getPassword();
    private static final ConnectionFactory factory =  () -> DriverManager.getConnection(URL, USERNAME, PASSWORD);

    public static void execute(String query) {
        execute(query, PreparedStatement::execute);
    }

    public static <T> T execute(String query, IExecutor<T> executor) {
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            return executor.execute(statement);
        } catch (SQLException ex) {
            throw new StorageException(ex);
        }
    }
}
