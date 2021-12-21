package com.resume.app.storage;

import com.resume.app.model.Resume;

public interface Storage {
    int getSize();

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();
}
