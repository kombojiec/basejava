package com.resume.app.storage;

import com.resume.app.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        return null;
    }

    protected int getResumePosition(Resume resume) {
        for (int i = 0; i < size; i++) {
            if(storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(int index, Resume resume) {
        storage[size++] = resume;
    }
}
