package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getResumePosition(resume);
        if(index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.printf("There is no match resume in database with uuid = %s\n", resume.getUuid());
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int index = getResumePosition(resume);
            if(index < 0) {
                storage[size++] = resume;
            } else {
                System.out.printf("There is same resume already in database with uuid = %s\n", resume.getUuid());
            }
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

    public void delete(String uuid) {
        Resume resume = get(uuid);
        if(resume != null) {
            resume = storage[--size];
            storage[size] = null;
        } else {
            System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private int getResumePosition(Resume resume) {
        for (int i = 0; i < size; i++) {
            if(storage[i].equals(resume)) {
                return i;
            }
        }
        return -1;
    }
}
