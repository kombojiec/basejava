package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected final static int STORAGE_SIZE = 100;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    protected int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getResumePosition(resume.getUuid());
        if(index >= 0) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        int index = getResumePosition(resume.getUuid());
        if(index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        if(size < STORAGE_SIZE) {
            insertElement(index, resume);
            ++size;
        } else {
            throw new StorageException(String.format("No empty space in database for new resume with uuid = %s\n", resume.getUuid()), resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getResumePosition(uuid);
        if(index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = getResumePosition(uuid);
        if(index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index -1);
            --size;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insertElement(int index, Resume resume);

    protected abstract int getResumePosition(String uuid);
}
