package com.resume.app.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super(String.format("There is same resume already in database with uuid = %s\n", uuid), uuid);
    }
}
