package ru.javarush.country.redis;

import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Continent;
import ru.javarush.country.entity.Country;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class CityCountry {

    private Integer id;
    private String name;
    private String district;
    private Integer population;
    private String countryCode;
    private String alternativeCountryCode;
    private String countryName;
    private Continent continent;
    private String countryRegion;
    private BigDecimal countrySurfaceArea;
    private Integer countryPopulation;
    private Set<Language> languages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAlternativeCountryCode() {
        return alternativeCountryCode;
    }

    public void setAlternativeCountryCode(String alternativeCountryCode) {
        this.alternativeCountryCode = alternativeCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public BigDecimal getCountrySurfaceArea() {
        return countrySurfaceArea;
    }

    public void setCountrySurfaceArea(BigDecimal countrySurfaceArea) {
        this.countrySurfaceArea = countrySurfaceArea;
    }

    public Integer getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Integer countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public void setCountry(Country country){
        setAlternativeCountryCode(country.getAlternativeCode());
        setContinent(country.getContinent());
        setCountryCode(country.getCode());
        setCountryName(country.getName());
        setCountryPopulation(country.getPopulation());
        setCountryRegion(country.getRegion());
        setCountrySurfaceArea(country.getSurfaceArea());
    }

    public void setCity(City city){
        setId(city.getId());
        setName(city.getName());
        setPopulation(city.getPopulation());
        setDistrict(city.getDistrict());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityCountry that)) return false;
        return  Objects.equals(id, that.getId())
                && Objects.equals(name, that.getName())
                && Objects.equals(district, that.getDistrict())
                && Objects.equals(population, that.getPopulation())
                && Objects.equals(countryCode, that.getCountryCode())
                && Objects.equals(alternativeCountryCode, that.getAlternativeCountryCode())
                && Objects.equals(countryName, that.getCountryCode())
                && Objects.equals(countryRegion, that.getCountryRegion())
                && Objects.equals(countrySurfaceArea, that.getCountrySurfaceArea())
                && Objects.equals(countryPopulation, that.countryPopulation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "CityCountry{" +
                "name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", countryCode='" + countryCode + '\'' +
                ", alternativeCountryCode='" + alternativeCountryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryRegion='" + countryRegion + '\'' +
                ", countrySurfaceArea=" + countrySurfaceArea +
                ", countryPopulation=" + countryPopulation +
                '}';
    }
}
