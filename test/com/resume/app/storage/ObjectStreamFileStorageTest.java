package com.resume.app.storage;

public class ObjectStreamFileStorageTest extends AbstractStorageTest{
    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR));
    }
}
