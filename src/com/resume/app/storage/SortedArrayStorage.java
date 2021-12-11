package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        int index = getResumePosition(resume);
        if(index >= 0) {
            return storage[index];
        }
        System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        return null;
    }

    @Override
    protected int getResumePosition(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void insertElement(int index, Resume resume) {
        System.arraycopy(storage, index, storage, index+1, storage.length - 1 - index);
        storage[index] = resume;
        ++size;
    }
}
