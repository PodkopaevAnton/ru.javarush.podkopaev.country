package ru.javarush.service;

import ru.javarush.entity.City;
import ru.javarush.redis.CityCountry;

import java.util.List;

public interface CityTransformer {
    List<CityCountry> transformCityData(List<City> cities);

}
