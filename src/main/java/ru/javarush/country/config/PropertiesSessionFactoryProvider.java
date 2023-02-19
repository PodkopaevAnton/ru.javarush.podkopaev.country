package ru.javarush.country.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

public class PropertiesSessionFactoryProvider implements SessionFactoryProvider {
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
    }
}
