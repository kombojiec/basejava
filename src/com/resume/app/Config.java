package com.resume.app;

import com.resume.app.storage.SqlStorage;
import com.resume.app.storage.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final String PROPS_PATH = "application.properties";
    private final Storage sqlStorage;
    private final String storageDir;

    private Config(){
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(PROPS_PATH)) {
            Properties props = new Properties();
            props.load(inputStream);
            storageDir = props.getProperty("storage.dir");
            this.sqlStorage = new SqlStorage(props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS_PATH);
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public String getStorageDir() {
        return storageDir;
    }

    public Storage getSqlStorage() {
        return sqlStorage;
    }
}
