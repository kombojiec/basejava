package com.resume.app.storage;

import com.resume.app.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    public void save(Resume resume) {
        if(getResumePosition(resume) >= 0) {
            System.out.printf("There is same resume already in database with uuid = %s\n", resume.getUuid());
            return;
        }
        if (size < STORAGE_SIZE) {
            storage[size++] = resume;
        } else {
            System.out.printf("No empty space in database for new resume with uuid = %s\n", resume.getUuid());
        }
    }

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
}
