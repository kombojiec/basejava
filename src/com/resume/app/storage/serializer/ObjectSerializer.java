package com.resume.app.storage.serializer;

import com.resume.app.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ObjectSerializer {
    void recordResume(Resume resume, OutputStream outputStream) throws IOException;
    Resume readResume(InputStream inputStream) throws IOException;

}
