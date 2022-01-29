package com.resume.app.storage;

import com.resume.app.storage.serializer.DataStreamSerializer;

public class DataFileStorageTest extends AbstractStorageTest {
    public DataFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamSerializer()));
    }
}
