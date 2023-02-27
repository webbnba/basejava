package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.List;

public class ResumeTestData {
    public static Resume resumeTest(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE_NUMBER, "555-55-55");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.ACCOUNT_LINKED_IN, "http://javaops-demo.ru/basejava/resume");
        resume.addContact(ContactType.ACCOUNT_GIT_HUB, "https://github.com/gkislin");
        resume.addContact(ContactType.ACCOUNT_STACK_OVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(List.of("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "\nС 2013 года: разработка проектов Разработка Web приложения", "Java Enterprise", "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)",
                "\nРеализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(List.of("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce")));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection("Java Online Projects", "http://javaops.ru/",
                List.of(new Period(2016, Month.APRIL, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."))));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection("RIT Center", "RIT_Center.com",
                List.of(new Period(2014, Month.OCTOBER, 2016, Month.JANUARY, "RIT Center", "\n" +
                        "Java архитектор\n" +
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO"))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection("Coursera", "https://www.coursera.org/course/progfun",
                List.of(new Period(2013, Month.JANUARY, 2013, Month.MARCH, "Coursera",
                        "'Functional Programming Principles in Scala' by Martin Odersky"))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                List.of(new Period(2011, Month.MAY, 2011, Month.APRIL, "Luxoft",
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"))));

        return resume;
    }
}
