package ru.javarush.dao;

import ru.javarush.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {

    List<City> getItems(int offset, int limit);

    int getTotalCount();

    Optional<City> getById(Integer id);
}
