package com.resume.app.storage.serializer;

import com.resume.app.model.Resume;
import com.resume.app.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements ObjectSerializer{
    @Override
    public void recordResume(Resume resume, OutputStream outputStream) throws IOException {
        try(Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, writer);
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try(Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Resume.class);
        }
    }
}
