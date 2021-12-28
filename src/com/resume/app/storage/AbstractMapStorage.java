package com.resume.app.storage;

import com.resume.app.comparator.ResumeComparator;
import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

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

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.entrySet().stream()
                .map(entry -> storage.get(entry.getKey()))
                .sorted(new ResumeComparator())
                .collect(Collectors.toList());
    }
}
