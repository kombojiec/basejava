package com.resume.app.storage;

import com.resume.app.exception.*;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void initialization() {
        storage.clear();
        storage.save(new Resume("uuid#1"));
        storage.save(new Resume("uuid#2"));
        storage.save(new Resume("uuid#3"));
    }

    @Test
    void getSize() {
        Assertions.assertEquals(3, storage.getSize());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.getSize());
    }

    @Test
    void update() {
        Resume resume = new Resume("uuid#1");
        storage.update(resume);
    }

    @Test
    void updateShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> {
            Resume resume = new Resume("uuid#4");
            storage.update(resume);
        });
    }

    @Test
    void save() {
        Resume resume = new Resume("uuid#4");
        storage.save(resume);
    }

    @Test
    void saveShouldThrowExistException() {
        assertThrows(ExistStorageException.class, () -> {
            Resume resume = new Resume("uuid#1");
            storage.save(resume);
        });
    }

    @Test
    void saveShouldNotThrowOverflowException() {
        assertDoesNotThrow(() -> {
            for (int i = 4; i <= 100 ; i++) {
                Resume resume = new Resume(String.format("uuid#%s", i));
                storage.save(resume);
            }
        });
    }

    @Test
    void saveShouldThrowOverflowException() {
        assertThrows(StorageException.class, () -> {
            for (int i = 4; i <= 101 ; i++) {
                Resume resume = new Resume(String.format("uuid#%s", i));
                storage.save(resume);
            }
        });
    }

    @Test
    void get() {
        Resume resume = storage.get("uuid#1");
        Assertions.assertNotNull(resume);
    }

    @Test
    void getShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("uuid#0");
        });
    }

    @Test
    void delete() {
        storage.delete("uuid#1");
    }

    @Test
    void deleteShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete("uuid#0");
        });
    }

    @Test
    void getAll() {
        assertEquals(3, storage.getAll().length);
    }
}