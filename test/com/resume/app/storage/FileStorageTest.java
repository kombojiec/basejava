package com.resume.app.storage;

import com.resume.app.storage.serializer.ObjectStreamSerializer;

public class FileStorageTest extends AbstractStorageTest{
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
