package com.resume.app;

import com.resume.app.model.Resume;
import com.resume.app.storage.ArrayStorage;
import com.resume.app.storage.Storage;

/**
 * Test for your com.resume.app.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
//    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid4");
        Resume r3 = new Resume("uuid1");
        Resume r4 = new Resume("uuid2");
        Resume r5 = new Resume("uuid1");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.getSize());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.getSize());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
