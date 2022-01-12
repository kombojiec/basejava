package com.resume.app.storage;

import com.resume.app.exception.StorageException;
import com.resume.app.model.Resume;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected final static int STORAGE_SIZE = 100;
    protected Resume[] storage = new Resume[STORAGE_SIZE];
    protected int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return getResumePosition(uuid);
    }

    @Override
    protected boolean isResumeExist(Integer index) {
        return (int) index >= 0;
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[(int) index] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Integer index) {
        if (size < STORAGE_SIZE) {
            insertElement((int) index, resume);
            ++size;
        } else {
            String message = String.format(
                    "No empty space in database for new resume with uuid = %s\n", resume.getUuid());
            throw new StorageException(message, resume.getUuid());
        }
    }

    @Override
    protected void deleteResume(Integer index) {
        int position = (int) index;
        System.arraycopy(storage, position + 1, storage, position, size - position - 1);
        --size;
    }

    @Override
    protected Stream<Resume> getAllResumeStream() {
        return Arrays.stream(Arrays.copyOf(storage, size));
    }

    protected abstract void insertElement(int index, Resume resume);

    protected abstract int getResumePosition(String uuid);
}
