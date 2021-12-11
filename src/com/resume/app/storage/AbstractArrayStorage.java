package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected final int STORAGE_SIZE = 10000;
    protected Resume[] storage;
    protected int size;

    {
        storage = new Resume[STORAGE_SIZE];
        size = 0;
    }

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
        int index = getResumePosition(resume);
        if(index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.printf("There is no match resume in database with uuid = %s\n", resume.getUuid());
    }

    @Override
    public abstract void save(Resume resume);

    @Override
    public abstract Resume get(String uuid);

    @Override
    public void delete(String uuid) {
        int index = getResumePosition(get(uuid));
        if(index != -1) {
            System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
            --size;
        } else {
            System.out.printf("There is no match resume in database with uuid = %s\n", uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getResumePosition(Resume resume);
}
