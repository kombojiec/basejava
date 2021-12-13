package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insertElement(int index, Resume resume) {
        index = Math.abs(++index);
        System.arraycopy(storage, index, storage, index+1, size - index);
        storage[index] = resume;
    }

    @Override
    protected int getResumePosition(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

}
