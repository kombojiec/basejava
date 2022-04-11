package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;
import com.resume.app.sql.SqlHelper;
import org.postgresql.util.PSQLException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlStorage implements Storage{
    private final SqlHelper sqlHelper;

    private final String DELETE_RESUMES = """
        DELETE FROM resumes;
    """;
    private final String GET_BY_UUID = """
            SELECT *
            FROM resumes
            WHERE uuid = ?;
            """;
    private final String SAVE_RESUME = """
            INSERT INTO resumes(uuid, full_name)
            VALUES (?, ?);
            """;
    private final String UPDATE_RESUME = """
            UPDATE resumes
            SET full_name = ?
            WHERE uuid = ?;
            """;
    private final String GET_ROWS_COUNT = """
            SELECT count(*) count
            FROM resumes;
            """;
    private final String DELETE_RESUME = """
            DELETE FROM resumes
            WHERE uuid = ?;
            """;

    public SqlStorage(String url, String username, String password) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(url, username, password));
    }

    @Override
    public int getSize() {
        return sqlHelper.execute(GET_ROWS_COUNT, statement -> {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute(DELETE_RESUMES);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute(UPDATE_RESUME, statement -> {
            statement.setString(1, resume.getFullName());
            statement.setString(2, resume.getUuid());
            int rows = statement.executeUpdate();
            if(rows == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return rows;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute(SAVE_RESUME, statement -> {
            statement.setString(1, resume.getUuid());
            statement.setString(2, resume.getFullName());
            try{
                statement.execute();
            } catch (PSQLException ex) {
                throw new ExistStorageException(resume.getUuid());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) throws FileNotFoundException {
        return sqlHelper.execute(GET_BY_UUID, statement -> {
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_Name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute(DELETE_RESUME, statement -> {
            statement.setString(1, uuid);
            int rows = statement.executeUpdate();
            if(rows == 0) {
                throw new NotExistStorageException(uuid);
            }
            return rows;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        String GET_ALL_RESUMES = """
                SELECT *
                FROM resumes;
                """;
        return sqlHelper.execute(GET_ALL_RESUMES, statement -> {
            ResultSet resultSet = statement.executeQuery();
            Storage storage = new SortedArrayStorage();
            while (resultSet.next()) {
                storage.save(new Resume(resultSet.getString("uuid"),
                        resultSet.getString("full_name")));
            }
            try {
                return storage.getAllSorted();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
