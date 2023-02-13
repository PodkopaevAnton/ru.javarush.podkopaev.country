package ru.javarush.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.config.PropertiesSessionFactoryProvider;
import ru.javarush.dao.CityDao;
import ru.javarush.dao.CityDaoImpl;
import ru.javarush.dao.CountryDao;
import ru.javarush.dao.CountryDaoImpl;
import ru.javarush.entity.City;
import ru.javarush.entity.Country;
import ru.javarush.entity.CountryLanguage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

public class SqlClient implements DataTestService {
    private final SessionFactory sessionFactory;
    private final CityDao cityDao;
    private final CountryDao countryDAO;

    public SqlClient(){
        PropertiesSessionFactoryProvider properties = new PropertiesSessionFactoryProvider();
        sessionFactory = properties.getSessionFactory();
        cityDao = new CityDaoImpl(sessionFactory);
        countryDAO = new CountryDaoImpl(sessionFactory);
    }

    public List<City> fetchCityData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDAO.getAll();
            int totalCount = cityDao.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDao.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }

    @Override
    public void getDataById(List<Integer> ids){
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            for (Integer id : ids){
                City city = cityDao.getById(id).get();
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }
}
