package com.resume.app.storage;

import com.resume.app.exception.*;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final Resume resume_1 = new Resume("uuid#1");
    private final Resume resume_2 = new Resume("uuid#2");
    private final Resume resume_3 = new Resume("uuid#3");
    private final Resume notExistResume = new Resume("notExist");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void initialization() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
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
        storage.update(resume_1);
        assertSame(resume_1, storage.get(resume_1.getUuid()));
    }

    @Test
    void updateShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> storage.update(notExistResume));
    }

    @Test
    void save() {
        storage.save(notExistResume);
        assertEquals(notExistResume, storage.get(notExistResume.getUuid()));
        assertEquals(4, storage.getSize());
    }

    @Test
    void saveShouldThrowExistException() {
        assertThrows(ExistStorageException.class, () -> storage.save(resume_1));
    }

    @Test
    void saveOverflowException() {
        assertDoesNotThrow(() -> {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_SIZE ; i++) {
                Resume resume = new Resume(String.format("uuid#%s", i));
                storage.save(resume);
            }
        });
        assertEquals(AbstractArrayStorage.STORAGE_SIZE, storage.getSize());
        assertThrows(StorageException.class, () -> storage.save(notExistResume));
    }

    @Test
    void get() {
        Resume resume = storage.get(resume_1.getUuid());
        assertEquals(resume, resume_1);
    }

    @Test
    void getShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> storage.get(notExistResume.getUuid()));
    }

    @Test
    void delete() {
        storage.delete(resume_1.getUuid());
        assertThrows(NotExistStorageException.class, () -> storage.get(resume_1.getUuid()));
        assertEquals(2, storage.getSize());
    }

    @Test
    void deleteShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(notExistResume.getUuid()));
    }

    @Test
    void getAll() {
        Resume[] resumeStorage = new Resume[]{resume_1, resume_2, resume_3};
        assertEquals(3, storage.getAll().length);
        assertArrayEquals(resumeStorage, storage.getAll());
    }
}