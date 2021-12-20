package com.resume.app.storage;

import com.resume.app.exception.NotExistStorageException;
import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage{
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
    public Resume get(String uuid) {
        int index = getResumePosition(uuid);
        if(index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Object getObjectKey(String uuid) {
        return getResumePosition(uuid);
    }

    @Override
    protected boolean isObjectExist(Object key) {
        return (int)key >= 0;
    }

    @Override
    protected void updateObject(Resume resume, Object key) {
        storage[(int)key] = resume;
    }

    @Override
    protected void saveObject(Resume resume, Object key) {
        if(size < STORAGE_SIZE) {
            insertElement((int)key, resume);
            ++size;
        } else {
            throw new StorageException(String.format("No empty space in database for new resume with uuid = %s\n", resume.getUuid()), resume.getUuid());
        }
    }

    @Override
    protected void deleteObject(Object key) {
        System.arraycopy(storage, (int)key + 1, storage, (int)key, size - (int)key -1);
            --size;
    }

    protected abstract void insertElement(int index, Resume resume);

    protected abstract int getResumePosition(String uuid);
}
