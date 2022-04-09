package com.resume.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final String PROPS_PATH = "application.properties";
    private final String storageDir;
    private final String URL;
    private final String username;
    private final String password;

    private Config(){
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(PROPS_PATH)) {
            Properties props = new Properties();
            props.load(inputStream);
            storageDir = props.getProperty("storage.dir");
            URL = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
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

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
