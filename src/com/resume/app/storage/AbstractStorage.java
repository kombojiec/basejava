package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage<T> implements Storage {

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume resume) {
        LOGGER.info("Update " + resume);
        updateResume(resume, getKeyOrResumeNotExistException(resume.getUuid()));
    }

    @Override
    public void save(Resume resume) {
        LOGGER.info("Save " + resume);
        T key = getSearchKey(resume.getUuid());
        if (isResumeExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, key);
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        deleteResume(getKeyOrResumeNotExistException(uuid));
    }

    @Override
    public Resume get(String uuid) throws FileNotFoundException {
        LOGGER.info("Get " + uuid);
        return getResume(getKeyOrResumeNotExistException(uuid));
    }

    private T getKeyOrResumeNotExistException(String uuid) {
        T key = getSearchKey(uuid);
        if (!isResumeExist(key)) {
            LOGGER.info("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("GetAllSorted");
        return getAllResumeStream()
                .sorted(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid))
                .collect(Collectors.toList());
    }

    protected abstract Stream<Resume> getAllResumeStream();

    protected abstract Resume getResume(T key) throws FileNotFoundException;

    protected abstract T getSearchKey(String uuid);

    protected abstract boolean isResumeExist(T key);

    protected abstract void updateResume(Resume resume, T key);

    protected abstract void saveResume(Resume resume, T key);

    protected abstract void deleteResume(T key);
}
