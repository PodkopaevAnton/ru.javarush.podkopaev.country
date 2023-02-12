package ru.javarush.dao;

import ru.javarush.entity.City;

import java.util.List;

public interface CityDaoImpl {

    List<City> getItems(int offset, int limit);

    int getTotalCount();

    City getById(Integer id);
}
