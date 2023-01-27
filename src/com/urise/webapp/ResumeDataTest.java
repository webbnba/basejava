package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeDataTest {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");


        List<String> achievementAndQualifications = new ArrayList<>();
        achievementAndQualifications.add("Достижения\n" +
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков");
        achievementAndQualifications.add("Квалификация\n" +
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        ListSection listSection = new ListSection(achievementAndQualifications);


        Period period = new Period(LocalDate.of(2022, 12, 3), LocalDate.of(2023, 3, 5), "Author", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Period> periodsList = new ArrayList<>();
        periodsList.add(period);
        OrganizationSection organizationSection = new OrganizationSection("EXPERIENCE", "exp@mail.ru", periodsList);


        TextSection textSection = new TextSection("Позиция\n" +
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n" +
                "Личные качества\n" +
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");


    }
}
