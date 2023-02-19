package ru.javarush.country.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.config.PropertiesSessionFactoryProvider;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.HibernateCityDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.dao.HibernateCountryDao;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

public class SqlService implements DataTestService {
    private final SessionFactory sessionFactory;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private static final int DATA_FETCH_STEP = 500;
    public SqlService(){
        PropertiesSessionFactoryProvider properties = new PropertiesSessionFactoryProvider();
        sessionFactory = properties.getSessionFactory();
        cityDao = new HibernateCityDao(sessionFactory);
        countryDao = new HibernateCountryDao(sessionFactory);
    }

    public List<City> fetchCityData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = countryDao.getAll();
            int totalCount = cityDao.getTotalCount();
            for (int i = 0; i < totalCount; i += DATA_FETCH_STEP) {
                allCities.addAll(cityDao.getItems(i, DATA_FETCH_STEP));
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
                Optional<Set<CountryLanguage>> optionalLanguages = cityDao.getById(id).map(city -> city.getCountry().getLanguages());
            }
            session.getTransaction().commit();
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
    }
}
