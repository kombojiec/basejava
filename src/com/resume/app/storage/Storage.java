package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.io.FileNotFoundException;
import java.util.List;

public interface Storage {
    int getSize();

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid) throws FileNotFoundException;

    void delete(String uuid);

    List<Resume> getAllSorted();
}
