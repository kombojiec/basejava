package com.resume.app;

import com.resume.app.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = createResume("uuid666", "Григорий Кислин");
        System.out.println(resume);

    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        fillResumeContacts(resume);
        fillResumeSections(resume);
        return resume;
    }

    private static void fillResumeContacts(Resume resume) {
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
    }

    private static void fillResumeSections(Resume resume) {
        SimpleLineSection objective = new SimpleLineSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, objective);

        SimpleLineSection personal = new SimpleLineSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. " +
                        "Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, personal);

        List<String> achievementList = new ArrayList<>();
        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django).");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListSection achievement = new ListSection(achievementList);
        resume.setSection(SectionType.ACHIEVEMENT, achievement);

        List<String> qualificationsList = new ArrayList<>();
        qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationsList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationsList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationsList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, " +
                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, " +
                "Selenium (htmlelements).");
        qualificationsList.add("Python: Django.");
        qualificationsList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationsList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationsList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT.");
        qualificationsList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationsList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualificationsList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualificationsList.add("Родной русский, английский \"upper intermediate\"");
        ListSection qualifications = new ListSection(qualificationsList);
        resume.setSection(SectionType.QUALIFICATIONS, qualifications);

        List<Organization> organizationList = new ArrayList<>();

        Link link = new Link("Java Online Projects", "https://javaops.ru/");
        Position position = new Position("Автор проекта.\n" +
                "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, " +
                "PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2013, 10, 1), LocalDate.now());
        Organization organization = new Organization("Java Online Projects", position);
        organization.setLink(link);
        organizationList.add(organization);

        position = new Position("Организация процесса разработки системы ERP " +
                "для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, " +
                "1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python",
                LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1));
        organization = new Organization("RIT Center", position);
        organizationList.add(organization);

        link = new Link("Luxoft (Deutsche Bank)", "https://career.luxoft.com/locations/russia/");
        position = new Position("Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, " +
                "Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
                "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.",
                LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));
        organization = new Organization("Luxoft (Deutsche Bank)", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Yota", "https://www.yota.ru/");
        position = new Position("Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));
        organization = new Organization("Yota", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Enkata", "https://www.pega.com/products/platform/robotic-process-automation");
        position = new Position("Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей " +
                "кластерного J2EE приложения (OLAP, Data mining).",
                LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1));
        organization = new Organization("Enkata", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Siemens AG", "https://new.siemens.com/ru/ru.html");
        position = new Position("Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                "на мобильной IN платформе Siemens @vantage (Java, Unix).",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1));
        organization = new Organization("Siemens AG", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Alcatel", "http://www.alcatel.ru/");
        position = new Position("Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1));
        organization = new Organization("Alcatel", position);
        organization.setLink(link);
        organizationList.add(organization);

        OrganizationSection workOrganizations = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EXPERIENCE, workOrganizations);

        organizationList = new ArrayList<>();
        link = new Link("Coursera", "https://www.coursera.org/learn/scala-functional-programming");
        position = new Position("\"Functional Programming Principles in Scala\" by Martin Odersky",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2014, 5, 1));
        organization = new Organization("Coursera", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Luxoft",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html");
        position = new Position("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1));
        organization = new Organization("Luxoft", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Siemens AG", "https://new.siemens.com/ru/ru.html");
        position = new Position("\t3 месяца обучения мобильным IN сетям (Берлин)",
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1));
        organization = new Organization("Siemens AG", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Alcatel", "http://www.alcatel.ru/");
        position = new Position("\t6 месяцев обучения цифровым телефонным сетям (Москва)",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1));
        organization = new Organization("Alcatel", position);
        organization.setLink(link);
        organizationList.add(organization);

        link = new Link("Санкт-Петербургский национальный исследовательский университет информационных " +
                "технологий, механики и оптики", "https://itmo.ru/ru/");
        position = new Position("Аспирантура (программист С, С++)",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1));
        organization = new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", position);
        organization.setLink(link);

        position = new Position("Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1));
        organization.setPosition(position);
        organizationList.add(organization);

        link = new Link("Заочная физико-техническая школа при МФТИ", "https://school.mipt.ru/");
        position = new Position("Закончил с отличием",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1));
        organization = new Organization("Заочная физико-техническая школа при МФТИ", position);
        organization.setLink(link);
        organizationList.add(organization);

        OrganizationSection educationOrganizations = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EDUCATION, educationOrganizations);
    }

}
