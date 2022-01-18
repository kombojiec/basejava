package com.resume.app.storage;

import com.resume.app.storage.serializer.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(),
                new ObjectStreamSerializer()));
    }
}
