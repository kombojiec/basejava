package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;
import com.resume.app.sql.SqlHelper;
import org.postgresql.util.PSQLException;

import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage{
    private final SqlHelper sqlHelper;

    public SqlStorage(String url, String username, String password) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(url, username, password));
    }

    @Override
    public int getSize() {
        return sqlHelper.execute("""
                SELECT count(*) count
                FROM resumes;
                """, statement -> {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("""
                    DELETE FROM resumes;
                """);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("""
                UPDATE resumes
                SET full_name = ?
                WHERE uuid = ?;
                """, statement -> {
            statement.setString(1, resume.getFullName());
            statement.setString(2, resume.getUuid());
            if(statement.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute("""
                INSERT INTO resumes(uuid, full_name)
                VALUES (?, ?);
                """, statement -> {
            statement.setString(1, resume.getUuid());
            statement.setString(2, resume.getFullName());
            try{
                statement.execute();
            } catch (PSQLException ex) {
                if(ex.getSQLState().equals("23505")) {
                    throw new ExistStorageException(resume.getUuid());
                } else {
                    throw new StorageException(ex);
                }
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) throws FileNotFoundException {
        return sqlHelper.execute("""
                SELECT *
                FROM resumes
                WHERE uuid = ?;
                """, statement -> {
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
        sqlHelper.execute("""
                DELETE FROM resumes
                WHERE uuid = ?;
                """, statement -> {
            statement.setString(1, uuid);
            if(statement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("""
                SELECT *
                FROM resumes
                ORDER BY full_name, uuid;
                """, statement -> {
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
