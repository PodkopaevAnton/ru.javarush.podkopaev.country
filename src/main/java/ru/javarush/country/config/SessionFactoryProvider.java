package ru.javarush.country.config;

import org.hibernate.SessionFactory;

public interface SessionFactoryProvider {
    SessionFactory getSessionFactory();
}
