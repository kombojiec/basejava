package com.resume.app.storage;

import com.resume.app.Config;

public class SqlStorageResumeTest extends AbstractStorageTest{
    public SqlStorageResumeTest() {
        super(Config.getInstance().getSqlStorage());
    }
}
