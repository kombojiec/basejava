package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;
import com.resume.app.storage.serializer.ObjectSerializer;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStorage extends AbstractStorage<File> {
    private final File storage;
    private final ObjectSerializer objectStorage;

    public FileStorage(File storage, ObjectSerializer objectStorage) {
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
    protected Stream<Resume> getAllResumeStream() {
        return Arrays.stream(getStorageFiles()).map(this::getResume);
    }

    @Override
    protected Resume getResume(File file) {
        try {
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
        } catch (IOException e) {
            throw new StorageException("Save resume error: ", file.getName(), e);
        }
        updateResume(resume, file);
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete resume error: ", file.getName());
        }
    }

    @Override
    public int getSize() {
        return (int)Arrays.stream(getStorageFiles()).count();
    }

    @Override
    public void clear() {
        Arrays.stream(getStorageFiles()).forEach(File::delete);
    }

    private File[] getStorageFiles() {
        File[] files = storage.listFiles();
        if (null == files) {
            throw new StorageException(storage.getName() + ".listFiles() equals null");
        }
        return files;
    }
}
