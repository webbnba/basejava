package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume resumeTest(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        contacts.put(ContactType.PHONE_NUMBER, "555-55-55");
        contacts.put(ContactType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactType.ACCOUNT_LINKED_IN, "http://javaops-demo.ru/basejava/resume");
        contacts.put(ContactType.ACCOUNT_GIT_HUB, "https://github.com/gkislin");
        contacts.put(ContactType.ACCOUNT_STACK_OVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(List.of("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "\nС 2013 года: разработка проектов Разработка Web приложения", "Java Enterprise", "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)",
                "\nРеализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(List.of("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce")));
        sections.put(SectionType.EXPERIENCE, new OrganizationSection("Java Online Projects", "http://javaops.ru/",
                List.of(new Period(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        sections.put(SectionType.EXPERIENCE, new OrganizationSection("RIT Center", "RIT_Center.com",
                List.of(new Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 01, 1), "RIT Center", "\n" +
                        "Java архитектор\n" +
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO"))));
        sections.put(SectionType.EDUCATION, new OrganizationSection("Coursera", "https://www.coursera.org/course/progfun",
                List.of(new Period(LocalDate.of(2013, 03, 1), LocalDate.of(2013, 05, 1), "Coursera",
                        "'Functional Programming Principles in Scala' by Martin Odersky"))));
        sections.put(SectionType.EDUCATION, new OrganizationSection("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                List.of(new Period(LocalDate.of(2011, 03, 1), LocalDate.of(2011, 04, 1), "Luxoft",
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"))));
        return resume;
    }
}
