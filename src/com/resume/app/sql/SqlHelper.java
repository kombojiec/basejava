package com.resume.app.sql;

import com.resume.app.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory factory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public SqlHelper(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void execute(String query) {
        execute(query, PreparedStatement::execute);
    }

    public  <T> T execute(String query, IExecutor<T> executor) {
        try (Connection connection = factory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            return executor.execute(statement);
        } catch (SQLException ex) {
            throw new StorageException(ex);
        }
    }
}
