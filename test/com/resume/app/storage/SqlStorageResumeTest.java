package com.resume.app.storage;

import com.resume.app.Config;

public class SqlStorageResumeTest extends AbstractStorageTest{
    private static final Config config = Config.getInstance();
    public SqlStorageResumeTest() {
        super(config.getSqlStorage());
    }
}
