package com.resume.app.storage;

import com.resume.app.comparator.ResumeComparator;
import com.resume.app.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((int) index);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.stream()
                .sorted(new ResumeComparator())
                .collect(Collectors.toList());
    }

    @Override
    protected Object getResumeKey(String str) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isResumeExist(Object key) {
        return (int) key >= 0;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage.add((int) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((int) key);
    }

}
