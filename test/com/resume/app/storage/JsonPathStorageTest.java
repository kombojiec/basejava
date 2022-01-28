package com.resume.app.storage;

import com.resume.app.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(),
                new JsonStreamSerializer()));
    }
}
