package ru.javarush.dao;

import ru.javarush.entity.Country;

import java.util.List;

public interface CountryDaoImpl {

    List<Country> getAll();
}
