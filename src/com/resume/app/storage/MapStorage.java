package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    @Override
    protected Object getResumeKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isResumeExist(Object key) {
        return storage.containsKey(key);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.put((String)key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put((String)key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(key);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }
}
