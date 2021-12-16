package com.resume.app.storage;

import com.resume.app.exception.*;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    private Storage storage;

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
        assertEquals(3, storage.getSize());
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
        assertTrue(resume.equals(storage.get(resume.getUuid())));
    }

    @Test
    void updateShouldThrowException() {
        Resume resume = new Resume("uuid#4");
        assertThrows(NotExistStorageException.class, () -> {
            storage.update(resume);
        });
    }

    @Test
    void save() {
        Resume resume = new Resume("uuid#4");
        storage.save(resume);
        assertNotNull(storage.get(resume.getUuid()));
        assertEquals(4, storage.getSize());
    }

    @Test
    void saveShouldThrowExistException() {
        Resume resume = new Resume("uuid#1");
        assertThrows(ExistStorageException.class, () -> {
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
        assertEquals(100, storage.getSize());
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
        assertNotNull(resume);
    }

    @Test
    void getShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("uuid#0");
        });
    }

    @Test
    void delete() {
        String uuid = "uuid#1";
        storage.delete(uuid);
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(uuid);
        });
        assertEquals(2, storage.getSize());
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