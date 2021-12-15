package com.resume.app.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super(String.format("There is no match resume in database with uuid = %s\n", uuid), uuid);
    }
}
