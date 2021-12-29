package com.resume.app.storage;

import com.resume.app.model.Resume;

public class MapStorage extends AbstractMapStorage {

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
        storage.put((String) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(key);
    }

}
