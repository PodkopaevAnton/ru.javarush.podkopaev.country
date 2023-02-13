package ru.javarush.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.entity.City;

import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDao {
    private final SessionFactory sessionFactory;

    public CityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c", City.class);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.list();
    }

    @Override
    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Override
    public Optional<City> getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID",id);
        return Optional.of(query.getSingleResult());
    }
}
