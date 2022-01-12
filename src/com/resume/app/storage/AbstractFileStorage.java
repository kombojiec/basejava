package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File storage;

    protected AbstractFileStorage(File storage) {
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
        return Arrays.stream(storage.listFiles())
                .map(this::getResume);
    }

    @Override
    protected Resume getResume(File file) {
        return readResume(file);
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
        saveResume(resume, file);
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            recordResume(resume, file);
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
        return storage.listFiles().length;
    }

    @Override
    public void clear() {
        for (File file : storage.listFiles()) {
            file.delete();
        }
    }

    protected abstract void recordResume(Resume resume, File file) throws IOException;

    protected abstract Resume readResume(File file);

}
