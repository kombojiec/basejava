package com.resume.app.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IExecutor<T> {
    T execute(PreparedStatement statement) throws SQLException;
}
