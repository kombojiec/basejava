package com.resume.app.storage;

import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final Resume resume_1 = new Resume("uuid#1", "uuid#1");
    private final Resume resume_2 = new Resume("uuid#2", "uuid#2");
    private final Resume resume_3 = new Resume("uuid#3", "uuid#3");
    private final Resume notExistResume = new Resume("notExist", "notExist");

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
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_SIZE; i++) {
                String uuid = String.format("uuid#%s", i);
                Resume resume = new Resume(uuid, uuid);
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
    void getAllSorted() {
        List<Resume> resumeStorage = Arrays.asList(resume_2, resume_3, resume_1);
        resumeStorage.sort(Comparator.<Resume>naturalOrder().thenComparing(Resume::getUuid));
        assertEquals(3, storage.getAllSorted().size());
        assertEquals(resumeStorage.get(0), storage.getAllSorted().get(0));
        assertEquals(resumeStorage.get(1), storage.getAllSorted().get(1));
        assertEquals(resumeStorage.get(2), storage.getAllSorted().get(2));
    }
}