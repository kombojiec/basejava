package com.resume.app.storage;

import com.resume.app.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAllResume() {
        return storage.entrySet().stream()
                .map(el -> el.getValue())
                .collect(Collectors.toList());
    }
}
