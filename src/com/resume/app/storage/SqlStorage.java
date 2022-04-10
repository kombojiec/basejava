package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;
import com.resume.app.sql.SqlHelper;

import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage{
    private final SqlHelper sqlHelper;

    private static final String DELETE_RESUMES = """
        DELETE FROM resumes;
    """;
    private static final String GET_BY_UUID = """
            SELECT *
            FROM resumes
            WHERE uuid = ?;
            """;
    private static final String SAVE_RESUME = """
            INSERT INTO resumes(uuid, full_name)
            VALUES (?, ?);
            """;
    private static final String UPDATE_RESUME = """
            UPDATE resumes
            SET full_name = ?
            WHERE uuid = ?;
            """;
    private static final String GET_ROWS_COUNT = """
            SELECT count(*) count
            FROM resumes;
            """;
    private static final String DELETE_RESUME = """
            DELETE FROM resumes
            WHERE uuid = ?;
            """;
    private static final String GET_ALL_RESUMES = """
            SELECT *
            FROM resumes;
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
            int rows;
            try{
                rows = statement.executeUpdate();
            } catch (SQLException ex) {
                throw new ExistStorageException(resume.getUuid());
            }
            return rows;
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
        return sqlHelper.execute(GET_ALL_RESUMES, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (resultSet.next()) {
                resumes.add(new Resume(resultSet.getString("uuid"),
                        resultSet.getString("full_name")));
            }
            return resumes;
        });
    }
}
