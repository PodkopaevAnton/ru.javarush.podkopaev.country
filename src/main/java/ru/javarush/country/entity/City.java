package ru.javarush.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "city",schema = "world")
public class City {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "district")
    private String district;

    @Column(name = "population")
    private Integer population;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        return  Objects.equals(id, city.getId())
                && Objects.equals(name, city.getName())
                && Objects.equals(country.getName(), city.getCountry().getName())
                && Objects.equals(district, city.getDistrict())
                && Objects.equals(population, city.getPopulation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country.getName(), district, population);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country=" + country.getName() +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
