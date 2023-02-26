package com.urise.webapp.storage.stratege;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements Serializer{
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for(Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                switch (entry.getKey()) {

                    case PERSONAL, OBJECTIVE -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(new TextSection().getText());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(String.valueOf(new ListSection().getList()));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(new OrganizationSection().getName());
                        dos.writeUTF(new OrganizationSection().getWebsite());
                        dos.writeUTF(new OrganizationSection().getPeriods().stream().map(Period::getStartDate)
                                .toString());
                        dos.writeUTF(new OrganizationSection().getPeriods().stream().map(Period::getEndDate)
                                .toString());
                        dos.writeUTF(new OrganizationSection().getPeriods().stream().map(Period::getTitle)
                                .toString());
                        dos.writeUTF(new OrganizationSection().getPeriods().stream().map(Period::getDescription)
                                .toString());
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                resume.addSection(SectionType.valueOf(dis.readUTF()), readSection(dis, SectionType.valueOf(dis.readUTF())));

            }
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection();
            case EXPERIENCE, EDUCATION -> new OrganizationSection();
        };
    }
}
