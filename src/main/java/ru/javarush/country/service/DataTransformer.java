package ru.javarush.country.service;

import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.redis.Language;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataTransformer {
    public List<CityCountry> transformCityData(List<City> cities) {
        return cities.stream().map(city -> {
            CityCountry res = new CityCountry();
            res.setCity(city);
            Country country = city.getCountry();
            res.setCountry(country);
            Set<CountryLanguage> countryLanguages = country.getLanguages();
            Set<Language> languages = countryLanguages.stream().map(cl -> {
                Language language = new Language();
                language.setCountryLanguage(cl);
                return language;
            }).collect(Collectors.toSet());
            res.setLanguages(languages);

            return res;
        }).collect(Collectors.toList());
    }
}
