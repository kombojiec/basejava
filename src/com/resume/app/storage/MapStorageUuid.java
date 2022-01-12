package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorageUuid extends AbstractStorage<String> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Stream<Resume> getAllResumeStream() {
        return storage.values().stream();
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isResumeExist(String key) {
        return storage.containsKey(key);
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        storage.put(key, resume);
    }

    @Override
    protected void saveResume(Resume resume, String key) {
        storage.put(key, resume);
    }

    @Override
    protected void deleteResume(String key) {
        System.out.println(key);
        Resume resume = storage.get(key);
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
}
