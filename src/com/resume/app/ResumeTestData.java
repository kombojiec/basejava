package com.resume.app;

import com.resume.app.model.*;
import com.resume.app.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = createResume("uuid666", "Григорий Кислин");
        System.out.println(resume);

    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
//        fillResumeContacts(resume);
//        fillResumeSections(resume);
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

        List<Organization.Position> positions = new ArrayList<>();
        Organization.Position position = new Organization.Position("Автор проекта.\n" +
                "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, " +
                "PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                DateUtil.of(2013, Month.OCTOBER), DateUtil.NOW);
        positions.add(position);
        Organization organization = new Organization("Java Online Projects",
                "https://javaops.ru/", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Организация процесса разработки системы ERP " +
                "для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. " +
                "Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, " +
                "1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python",
                DateUtil.of(2012, Month.APRIL), DateUtil.of(2014, Month.OCTOBER));
        positions.add(position);
        organization = new Organization("RIT Center", null, positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, " +
                "Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области " +
                "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.",
                DateUtil.of(2010, Month.DECEMBER), DateUtil.of(2012, Month.APRIL));
        positions.add(position);
        organization = new Organization("Luxoft (Deutsche Bank)", "https://career.luxoft.com/locations/russia/", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                DateUtil.of(2008, Month.JUNE), DateUtil.of(2010, Month.DECEMBER));
        positions.add(position);
        organization = new Organization("Yota", "https://www.yota.ru/", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей " +
                "кластерного J2EE приложения (OLAP, Data mining).",
                DateUtil.of(2007, Month.MARCH),
                DateUtil.of(2008, Month.JANUARY));
        positions.add(position);
        organization = new Organization("Enkata", "https://www.pega.com/products/platform/robotic-process-automation", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                "на мобильной IN платформе Siemens @vantage (Java, Unix).",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2007, Month.FEBRUARY));
        positions.add(position);
        organization = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2005, Month.JANUARY));
        positions.add(position);
        organization = new Organization("Alcatel", "http://www.alcatel.ru/", positions);
        organizationList.add(organization);

        OrganizationSection workOrganizations = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EXPERIENCE, workOrganizations);

        organizationList = new ArrayList<>();
        positions = new ArrayList<>();
        position = new Organization.Position("\"Functional Programming Principles in Scala\" by Martin Odersky",
                DateUtil.of(2013, Month.MARCH),
                DateUtil.of(2014, Month.MAY));
        positions.add(position);
        organization = new Organization("Coursera",
                "https://www.coursera.org/learn/scala-functional-programming", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Курс \"Объектно-ориентированный анализ ИС. " +
                "Концептуальное моделирование на UML.\"",
                DateUtil.of(2011, Month.MARCH),
                DateUtil.of(2011, Month.APRIL));
        positions.add(position);
        organization = new Organization("Luxoft",
                "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("\t3 месяца обучения мобильным IN сетям (Берлин)",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2005, Month.APRIL));
        positions.add(position);
        organization = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("\t6 месяцев обучения цифровым телефонным сетям (Москва)",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(1998, Month.MARCH));
        positions.add(position);
        organization = new Organization("Alcatel", "http://www.alcatel.ru/", positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Аспирантура (программист С, С++)",
                DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY));
        positions.add(position);

        //Строка с нулём вместо описания для теста
//        position = new Organization.Position("Инженер (программист Fortran, C)",
        position = new Organization.Position(null,
                DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY));
        positions.add(position);
        //Строка с нулём вместо URL для теста
//        organization = new Organization("Санкт-Петербургский национальный исследовательский университет " +
//                "информационных технологий, механики и оптики", "https://itmo.ru/ru/", positions);
        organization = new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", null, positions);
        organizationList.add(organization);

        positions = new ArrayList<>();
        position = new Organization.Position("Закончил с отличием",
                DateUtil.of(1984, Month.SEPTEMBER),
                DateUtil.of(1987, Month.JUNE));
        positions.add(position);
        organization = new Organization("Заочная физико-техническая школа при МФТИ",
                "https://school.mipt.ru/", positions);
        organizationList.add(organization);

        OrganizationSection educationOrganizations = new OrganizationSection(organizationList);
        resume.setSection(SectionType.EDUCATION, educationOrganizations);
    }

}
