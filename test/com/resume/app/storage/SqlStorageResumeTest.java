package com.resume.app.storage;

import com.resume.app.Config;

public class SqlStorageResumeTest extends AbstractStorageTest{
    private static final Config config = Config.getInstance();
    public SqlStorageResumeTest() {
        super(new SqlStorage());
//        super(new SqlStorage(config.getURL(), config.getUsername(), config.getPassword()));
    }
}
