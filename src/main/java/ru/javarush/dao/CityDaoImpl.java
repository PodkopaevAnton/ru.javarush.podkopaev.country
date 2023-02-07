package ru.javarush.dao;

import ru.javarush.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDaoImpl {

    public List<City> getItems(int offset, int limit);

    public int getTotalCount();

    public Optional<City> getById(Integer id);
}
