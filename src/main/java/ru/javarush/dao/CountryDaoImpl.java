package ru.javarush.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.entity.Country;

import java.util.List;

public class CountryDaoImpl implements CountryDao {
    private final SessionFactory sessionFactory;

    public CountryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class);
        return query.list();
    }
}
