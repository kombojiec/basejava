package com.resume.app.storage.serializer;

import com.resume.app.model.*;
import com.resume.app.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements ObjectSerializer {

    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class,
                SimpleLineSection.class, ListSection.class, Organization.Position.class);
    }


    @Override
    public void recordResume(Resume resume, OutputStream outputStream) throws IOException {
        xmlParser.marshall(resume, new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try {
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return xmlParser.unmarshall(reader);
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }

    }
}
