package com.resume.app.storage.serializer.interfaces;

import java.io.IOException;

public interface CustomReader<T> {
    T read() throws IOException;
}
