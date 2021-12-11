package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume resume) {
        if(getResumePosition(resume) >= 0) {
            System.out.printf("There is same resume already in database with uuid = %s\n", resume.getUuid());
            return;
        }
        if(size == 0) {
            storage[size++] = resume;
        } else if(size < STORAGE_SIZE) {
            for (int i = 0; i < size; i++) {
                if(resume.getUuid().compareTo(storage[i].getUuid()) <= 0) {
                    System.arraycopy(storage, i, storage, i+1, storage.length - 1 - i);
                    storage[i] = resume;
                    ++size;
                    return;
                }
            }
            storage[size++] = resume;
            return;
        } else {
            System.out.printf("No empty space in database for new resume with uuid = %s\n", resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        int index = getResumePosition(resume);
        if(index != -1) {
            return storage[index];
        }
        System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        return null;
    }

    @Override
    protected int getResumePosition(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
