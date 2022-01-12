package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorageResumeKey extends AbstractStorage<Resume> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Stream<Resume> getAllResumeStream() {
        return storage.values().stream();
    }

    @Override
    protected Resume getResume(Resume key) {
        return key;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isResumeExist(Resume key) {
        return key != null;
    }

    @Override
    protected void updateResume(Resume resume, Resume key) {
        storage.put(resume.getUuid(), key);
    }

    @Override
    protected void saveResume(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Resume key) {
        storage.remove(key.getUuid());
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
