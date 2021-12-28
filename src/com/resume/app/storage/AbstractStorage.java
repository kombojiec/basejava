package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object key = getResumeKey(resume.getUuid());
        resumeNotExist(key, resume.getUuid());
        updateResume(resume, key);
    }

    @Override
    public void save(Resume resume) {
        Object key = getResumeKey(resume.getUuid());
        if (isResumeExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, key);
        }
    }

    @Override
    public void delete(String uuid) {
        Object key = getResumeKey(uuid);
        resumeNotExist(key, uuid);
        deleteResume(key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getResumeKey(uuid);
        resumeNotExist(key, uuid);
        return getResume(key);
    }

    private void resumeNotExist(Object key, String uuid) {
        if (!isResumeExist(key)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Resume getResume(Object key);

    protected abstract Object getResumeKey(String uuid);

    protected abstract boolean isResumeExist(Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);
}
