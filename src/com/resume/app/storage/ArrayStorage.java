package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int position = getResumePosition(resume);
        if(position >= 0) {
            storage[position] = resume;
            return;
        }
        System.out.println("There is no match resume in database");
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int position = getResumePosition(resume);
            if(position < 0) {
                storage[size++] = resume;
            } else {
                System.out.println("There is same resume already in database");
            }
        } else {
            System.out.println("No empty space in database for new resume");
        }
    }

    public Resume get(String uuid) {
        Resume resume = getResumeById(uuid);
        if(resume != null) {
            return resume;
        }
        System.out.printf("There is no resume with uuid = %s in database\n", uuid);
        return null;
    }

    public void delete(String uuid) {
        Resume resume = getResumeById(uuid);
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

    private Resume getResumeById(String uuid) {
        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}
