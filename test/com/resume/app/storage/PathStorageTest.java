package com.resume.app.storage;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(),
                new PathStorageUtil()));
    }
}
