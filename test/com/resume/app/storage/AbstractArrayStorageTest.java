package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
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

}