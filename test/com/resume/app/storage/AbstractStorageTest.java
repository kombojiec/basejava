package com.resume.app.storage;

import com.resume.app.ResumeTestData;
import com.resume.app.exception.ExistStorageException;
import com.resume.app.exception.NotExistStorageException;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File("storage");
    protected final Storage storage;
    private final Resume resume_1 = ResumeTestData.createResume("uuid#1", "uuid#1");
    private final Resume resume_2 = ResumeTestData.createResume("uuid#2", "uuid#2");
    private final Resume resume_3 = ResumeTestData.createResume("uuid#3", "uuid#3");
    protected final Resume notExistResume = ResumeTestData.createResume("notExist", "notExist");

    public AbstractStorageTest(Storage storage) {
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
    void update() throws FileNotFoundException {
        storage.update(resume_1);
        assertSame(resume_1, storage.get(resume_1.getUuid()));
    }

    @Test
    void updateShouldThrowException() {
        assertThrows(NotExistStorageException.class, () -> storage.update(notExistResume));
    }

    @Test
    void save() throws FileNotFoundException {
        storage.save(notExistResume);
        assertEquals(notExistResume, storage.get(notExistResume.getUuid()));
        assertEquals(4, storage.getSize());
    }

    @Test
    void saveShouldThrowExistException() {
        assertThrows(ExistStorageException.class, () -> storage.save(resume_1));
    }

    @Test
    void get() throws FileNotFoundException {
        Resume resume = storage.get(resume_1.getUuid());
        assertEquals(resume_1, resume);
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
        resumeStorage.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        assertEquals(3, storage.getAllSorted().size());
        assertEquals(resumeStorage, storage.getAllSorted());
    }

}
