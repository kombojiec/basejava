package com.resume.app.storage;

import com.resume.app.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void insertElement(int index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected int getResumePosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
