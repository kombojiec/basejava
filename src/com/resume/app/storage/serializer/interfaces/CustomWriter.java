package com.resume.app.storage.serializer.interfaces;

import java.io.IOException;

public interface CustomWriter<T> {
    void write(T t) throws IOException;
}
