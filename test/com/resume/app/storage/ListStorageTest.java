package com.resume.app.storage;

import org.junit.jupiter.api.Disabled;

public class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Disabled
    @Override
    void saveOverflowException() {
        super.saveOverflowException();
    }
}
