package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.io.*;

public class PathStorageUtil implements ObjectStorage {

    @Override
    public void recordResume(Resume resume, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
