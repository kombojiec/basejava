package com.resume.app.storage;

import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListStorage extends AbstractStorage{
    List<Resume> storage = new ArrayList<>();

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume get(String uuid) {
        int index = (int)getObjectKey(uuid);
        if(index >= 0) {
            return storage.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        if(!storage.removeIf(el -> el.getUuid().equals(uuid))) {
            throw new NotExistStorageException(uuid);
        }

    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    protected Object getObjectKey(String str) {
        Optional resume = storage.stream().filter(el -> el.getUuid().equals(str)).findFirst();
        if(resume.isPresent()) {
            return storage.indexOf(resume.get());
        }
        return -1;
    }

    @Override
    protected boolean isObjectExist(Object key) {
        return (int)key >= 0;
    }

    @Override
    protected void updateObject(Resume resume, Object key) {
        storage.add((int)key, resume);
    }

    @Override
    protected void saveObject(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    protected void deleteObject(Object key) {
        storage.remove((int)key);
    }

}
