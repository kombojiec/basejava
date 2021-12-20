package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

public abstract class AbstractStorage implements Storage{

    @Override
    public void update(Resume resume) {
        Object key = getObjectKey(resume.getUuid());
        if(isObjectExist(key)) {
            updateObject(resume, key);
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        Object key = getObjectKey(resume.getUuid());
        if(isObjectExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveObject(resume, key);
        }
    }

    @Override
    public void delete(String uuid) {
        Object key = getObjectKey(uuid);
        if(isObjectExist(key)) {
            deleteObject(key);
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Object getObjectKey(String uuid);
    protected abstract boolean isObjectExist(Object key);
    protected abstract void updateObject(Resume resume, Object key);
    protected abstract void saveObject(Resume resume, Object key);
    protected abstract void deleteObject(Object key);
}
