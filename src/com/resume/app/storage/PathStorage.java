package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;
import com.resume.app.storage.serializer.ObjectSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path storage;
    private final ObjectSerializer objectStorage;

    protected PathStorage(String path, ObjectSerializer objectStorage) {
        this.objectStorage = objectStorage;
        storage = Paths.get(path);
        Objects.requireNonNull(storage, "Directory can't be null");
        if (!Files.isDirectory(storage)) {
            throw new IllegalArgumentException(path + " is not directory");
        } else if (!Files.isReadable(storage) || !Files.isWritable(storage)) {
            throw new IllegalArgumentException(path + " is not readable/writable");
        }
    }

    @Override
    protected Stream<Resume> getAllResumeStream() {
        return getPathStream().map(this::getResume);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return objectStorage.readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("getResume error ", path.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return storage.resolve(uuid);
    }

    @Override
    protected boolean isResumeExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            objectStorage.recordResume(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Update resume error: ", path.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Save resume error: ", path.toAbsolutePath().toString(), e);
        }
        updateResume(resume, path);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("deleteResume error: ", path.toAbsolutePath().toString(), e);
        }
    }

    @Override
    public int getSize() {
        return (int)getPathStream().count();
    }

    @Override
    public void clear() {
        getPathStream().forEach(this::deleteResume);
    }

    private Stream<Path> getPathStream() {
        try{
            return Files.list(storage);
        } catch (IOException e) {
            throw new StorageException("Storage folder " + storage.getFileName() + " is incorrect", e);
        }
    }

}
