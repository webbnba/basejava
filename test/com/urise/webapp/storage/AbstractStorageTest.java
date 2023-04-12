package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;
    private static final String UUID_1 = String.valueOf(UUID.randomUUID());
    private static final String UUID_2 = String.valueOf(UUID.randomUUID());;
    private static final String UUID_3 = String.valueOf(UUID.randomUUID());;
    private static final String UUID_4 = String.valueOf(UUID.randomUUID());;

    private static final Resume RESUME_1 = ResumeTestData.resumeTest(UUID_1, "fullName1");
    private static final Resume RESUME_2 = ResumeTestData.resumeTest(UUID_2, "fullName2");
    private static final Resume RESUME_3 = ResumeTestData.resumeTest(UUID_3, "fullName3");

    private static final Resume RESUME_4 = ResumeTestData.resumeTest(UUID_4, "fullName4");

    private static final String UUID_NOT_EXIST = "dummy";

    Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertSize(3);
    }

    private void assertSize(int countResume) {
         assertEquals(countResume, storage.size());
    }

    @Test
    void getAll() {
        assertArrayEquals(expected, storage.getAllSorted().toArray());
        assertSize(3);
    }


    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(new Storage[0], storage.getAllSorted().toArray());
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveIfExist() throws ExistStorageException {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_3));
    }

    @Test
    void delete() throws NotExistStorageException {
        storage.delete(RESUME_2.getUuid());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.delete(RESUME_2.getUuid()));
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        newResume.addContact(ContactType.PHONE_NUMBER, "234-567");
        newResume.addContact(ContactType.SKYPE, "newSkype");
        newResume.addContact(ContactType.EMAIL, "newEmail@dog.ru");
        newResume.addContact(ContactType.ACCOUNT_GIT_HUB, "alexGit");
        newResume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        newResume.addSection(SectionType.ACHIEVEMENT, new ListSection(List.of("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет")));
//                "С 2013 года: разработка проектов Разработка Web приложения", "Java Enterprise", "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)",
//                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")));
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    void updateIfNotExistResume() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume(UUID_NOT_EXIST, "fullName")));
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getNotExist() throws NotExistStorageException {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }
}