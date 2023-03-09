package com.urise.webapp.storage.stratege;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();

            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });


//            Map<SectionType, AbstractSection> sections = r.getSections();

            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        String text = ((TextSection) section).getText();
                        dos.writeUTF(text);
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        writeCollection(dos, ((ListSection) section).getList(), dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {

                        dos.writeUTF(((OrganizationSection) section).getName());
                        dos.writeUTF(((OrganizationSection) section).getWebsite());

                        writeCollection(dos, ((OrganizationSection) section).getPeriods(), period -> {
                            writeLocalDate(dos, period.getStartDate());
                            writeLocalDate(dos, period.getEndDate());
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        } );
                    }
                }
            });
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
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));

            }
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(dis.readUTF(), dis.readUTF(), readList(dis
                    , () -> new Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF())));
        };
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }
    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }
    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
