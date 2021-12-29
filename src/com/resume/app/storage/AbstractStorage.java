package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        updateResume(resume, resumeNotExist(resume.getUuid()));
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
        deleteResume(resumeNotExist(uuid));
    }

    @Override
    public Resume get(String uuid) {
        return getResume(resumeNotExist(uuid));
    }

    private Object resumeNotExist(String uuid) {
        Object key = getResumeKey(uuid);
        if (!isResumeExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getAllResume();
        return list.stream()
                .sorted(Comparator.<Resume>naturalOrder().thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    protected abstract List<Resume> getAllResume();

    protected abstract Resume getResume(Object key);

    protected abstract Object getResumeKey(String uuid);

    protected abstract boolean isResumeExist(Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);
}
