package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path storage;

    protected AbstractPathStorage(String path) {
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
        try {
            return Files.list(storage)
                    .map(this::getResume);
        } catch (IOException e) {
            throw new StorageException("getResume error ", null, e);
        }
    }

    @Override
    protected Resume getResume(Path Path) {
        try {
            return readResume(new BufferedInputStream(new FileInputStream(Path.toFile())));
        } catch (IOException e) {
            throw new StorageException("getResume error ", Path.toAbsolutePath().toString(), e);
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
    protected void updateResume(Resume resume, Path Path) {
        try {
            recordResume(resume, new BufferedOutputStream(new FileOutputStream(Path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Update resume error: ", Path.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
            updateResume(resume, path);
        } catch (IOException e) {
            throw new StorageException("Save resume error: ", path.toAbsolutePath().toString(), e);
        }
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
        try {
            Long size = Files.list(storage).count();
            return Math.toIntExact(size);
        } catch (IOException e) {
            throw new StorageException("getSize error: ", null, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(storage).forEach(this::deleteResume);
        } catch (IOException ex) {
            throw new StorageException("Clear method error", null, ex);
        }
    }

    protected abstract void recordResume(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume readResume(InputStream inputStream) throws IOException;

}
