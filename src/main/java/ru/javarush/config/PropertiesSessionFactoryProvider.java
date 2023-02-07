package ru.javarush.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javarush.entity.City;
import ru.javarush.entity.Country;
import ru.javarush.entity.CountryLanguage;

public class PropertiesSessionFactoryProvider implements SessionFactoryProvider{
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
    }
}
