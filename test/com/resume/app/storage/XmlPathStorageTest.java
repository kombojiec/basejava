package com.resume.app.storage;

import com.resume.app.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(),
                new XmlStreamSerializer()));
    }
}
