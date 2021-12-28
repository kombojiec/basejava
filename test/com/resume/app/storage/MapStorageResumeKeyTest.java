package com.resume.app.storage;

import org.junit.jupiter.api.Disabled;

public class MapStorageResumeKeyTest extends AbstractArrayStorageTest {
    public MapStorageResumeKeyTest() {
        super(new MapStorageResumeKey());
    }

    @Disabled
    @Override
    void saveOverflowException() {
    }
}
