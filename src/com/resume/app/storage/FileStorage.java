package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStorage extends AbstractStorage<File> {
    private final File storage;
    private final ObjectStorage objectStorage;

    public FileStorage(File storage, ObjectStorage objectStorage) {
        this.objectStorage = objectStorage;
        Objects.requireNonNull(storage, "Directory can't be null");
        if (!storage.isDirectory()) {
            throw new IllegalArgumentException(storage.getName() + " is not directory");
        } else if (!storage.canRead() || !storage.canWrite()) {
            throw new IllegalArgumentException(storage.getName() + " is not readable/writable");
        }
        this.storage = storage;
    }

    @Override
    protected Stream<Resume> getAllResumeStream()  {
        return Arrays.stream(Objects.requireNonNull(storage.listFiles()))
                .map(this::getResume);
    }

    @Override
    protected Resume getResume(File file) {
        try{
            return objectStorage.readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException ex) {
            throw new StorageException("getResume error ", file.getName(), ex);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(storage, uuid);
    }

    @Override
    protected boolean isResumeExist(File file) {
        return file.exists();
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            objectStorage.recordResume(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Update resume error: ", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            updateResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("Save resume error: ", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        file.delete();
    }

    @Override
    public int getSize() {

        return storage.listFiles() == null? 0: Objects.requireNonNull(storage.listFiles()).length;
    }

    @Override
    public void clear() {
        if(storage.listFiles() != null) {
            for (File file : Objects.requireNonNull(storage.listFiles())) {
                file.delete();
            }
        }
    }

}
