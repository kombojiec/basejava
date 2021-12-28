package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractMapStorage {
    private Map<String, Resume> storage = new HashMap<>();

}
