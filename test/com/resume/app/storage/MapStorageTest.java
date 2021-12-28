package com.resume.app.storage;

import org.junit.jupiter.api.Disabled;

public class MapStorageTest extends AbstractArrayStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Disabled
    @Override
    void saveOverflowException() {
    }
}
