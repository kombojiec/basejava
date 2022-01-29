package com.resume.app.storage.serializer;

import com.resume.app.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements ObjectSerializer {
    @Override
    public void recordResume(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().toString());
                dos.writeUTF(entry.getValue());
            }
            int sectionsSize = resume.getSections().size();
            dos.writeInt(sectionsSize);
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType sectiontype = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(sectiontype.toString());
                switch (sectiontype) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((SimpleLineSection) section).getContent());
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> elements = ((ListSection) section).getElements();
                        dos.writeInt(elements.size());
                        for (String element : elements) {
                            dos.writeUTF(element);
                        }
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(organizations.size());
                        organizations.stream().forEach(organization -> {
                            try {
                                dos.writeUTF(organization.getLink().getName());
                                dos.writeUTF(organization.getLink().getUrl());
                                List<Organization.Position> positions = organization.getPositions();
                                dos.writeInt(positions.size());
                                positions.stream().forEach(position -> {
                                    try {
                                        dos.writeUTF(position.getDescription());
                                        dos.writeUTF(position.getStartDate().toString());
                                        dos.writeUTF(position.getEndDate().toString());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        break;
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
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            Map<SectionType, AbstractSection> sections = new HashMap<>();
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType section = SectionType.valueOf(dis.readUTF());
                switch (section) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(section, new SimpleLineSection(dis.readUTF()));
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> elements = new ArrayList<>();
                        int elementsSize = dis.readInt();
                        for (int j = 0; j < elementsSize; j++) {
                            elements.add(dis.readUTF());
                        }
                        resume.setSection(section, new ListSection(elements));
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        int organizationsSize = dis.readInt();
                        for (int j = 0; j < organizationsSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            List<Organization.Position> positions = new ArrayList<>();
                            int positionsSize = dis.readInt();
                            for (int k = 0; k < positionsSize; k++) {
                                Organization.Position position = new Organization.Position(
                                        dis.readUTF(), LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF())
                                );
                                positions.add(position);
                            }
                            organizations.add(new Organization(name, url, positions));
                        }
                        resume.setSection(section, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }
}
