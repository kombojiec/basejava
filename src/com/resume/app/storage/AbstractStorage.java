package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        updateResume(resume, getKeyOrResumeNotExistException(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        Object key = getResumeKey(resume.getUuid());
        if (isResumeExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, key);
    }

    @Override
    public void delete(String uuid) {
        deleteResume(getKeyOrResumeNotExistException(uuid));
    }

    @Override
    public Resume get(String uuid) {
        return getResume(getKeyOrResumeNotExistException(uuid));
    }

    private Object getKeyOrResumeNotExistException(String uuid) {
        Object key = getResumeKey(uuid);
        if (!isResumeExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        return getAllResume()
                .sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    protected abstract Stream<Resume> getAllResume();

    protected abstract Resume getResume(Object key);

    protected abstract Object getResumeKey(String uuid);

    protected abstract boolean isResumeExist(Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);
}
