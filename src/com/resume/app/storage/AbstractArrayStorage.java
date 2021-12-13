package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected final static int STORAGE_SIZE = 10000;
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
        System.out.printf("There is no match resume in database with uuid = %s\n", resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        int index = getResumePosition(resume.getUuid());
        if(index >= 0) {
            System.out.printf("There is same resume already in database with uuid = %s\n", resume.getUuid());
            return;
        }
        if(size < STORAGE_SIZE) {
            insertElement(index, resume);
            ++size;
        } else {
            System.out.printf("No empty space in database for new resume with uuid = %s\n", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getResumePosition(uuid);
        if(index >= 0) {
            return storage[index];
        }
        System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = getResumePosition(uuid);
        if(index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            --size;
        } else {
            System.out.printf("There is no match resume in database with uuid = %s\n", uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insertElement(int index, Resume resume);

    protected abstract int getResumePosition(String uuid);
}
