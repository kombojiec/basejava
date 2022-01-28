package com.resume.app.storage;

import com.resume.app.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(),
                new ObjectStreamSerializer()));
    }
}
