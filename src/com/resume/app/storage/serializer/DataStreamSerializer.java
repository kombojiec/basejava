package com.resume.app.storage.serializer;

import com.resume.app.model.*;
import com.resume.app.storage.serializer.interfaces.CustomConsumer;
import com.resume.app.storage.serializer.interfaces.CustomWriter;
import com.resume.app.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamSerializer implements ObjectSerializer {

    @Override
    public void recordResume(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            int sectionsSize = resume.getSections().size();
            dos.writeInt(sectionsSize);
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType sectiontype = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(sectiontype.name());
                switch (sectiontype) {
                    case OBJECTIVE, PERSONAL -> dos.writeUTF(((SimpleLineSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeWithException(((ListSection) section).getElements(),
                            dos, dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> {
                        writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                            Link link = organization.getLink();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeWithException(organization.getPositions(), dos, position -> {
                                dos.writeUTF(position.getDescription());
                                writeDates(position.getStartDate(), position.getEndDate(), dos);
                            });
                        });
                    }
                }
            }
        }
    }

    @Override
    public Resume readResume(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dis, () -> {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            });
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType section = SectionType.valueOf(dis.readUTF());
                resume.setSection(section, readSection(dis, section));
            }
            return resume;
        }
    }

    private void writeDates(LocalDate startDate, LocalDate endDate, DataOutputStream stream) throws IOException {
        stream.writeInt(startDate.getYear());
        stream.writeUTF(startDate.getMonth().name());
        stream.writeInt(endDate.getYear());
        stream.writeUTF(endDate.getMonth().name());
    }

    private LocalDate readDate(DataInputStream stream) throws IOException {
        return DateUtil.of(stream.readInt(), Month.valueOf(stream.readUTF()));
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream stream, CustomWriter<T> writer) throws IOException {
        stream.writeInt(collection.size());
        for(T el: collection) {
            writer.write(el);
        }
    }

    private void readElements(DataInputStream stream, CustomConsumer consumer) throws IOException{
        int size = stream.readInt();
        while(--size >= 0) {
            consumer.accept();
        }
    }

    private AbstractSection readSection(DataInputStream stream, SectionType type) throws IOException {
        switch (type) {
            case OBJECTIVE:
            case PERSONAL:
                return new SimpleLineSection(stream.readUTF());

            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> elements = new ArrayList<>();
                readElements(stream, () -> elements.add(stream.readUTF()));
                return new ListSection(elements);

            case EXPERIENCE:
            case EDUCATION:
                List<Organization> organizations = new ArrayList<>();
                readElements(stream, () -> {
                    String name = stream.readUTF();
                    String url = stream.readUTF();
                    List<Organization.Position> positions = new ArrayList<>();
                    readElements(stream, () -> {
                        Organization.Position position = new Organization.Position(stream.readUTF(),
                                readDate(stream),
                                readDate(stream)
                        );
                        positions.add(position);
                    });
                    organizations.add(new Organization(name, url, positions));
                });
                return new OrganizationSection(organizations);
            default: return null;
        }
    }

}
