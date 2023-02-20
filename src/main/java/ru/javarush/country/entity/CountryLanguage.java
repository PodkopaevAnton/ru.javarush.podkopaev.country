package ru.javarush.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "country_language",schema = "world")
public class CountryLanguage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "language")
    private String language;

    @Column(name = "is_official",columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isOfficial;

    @Column(name = "percentage")
    private BigDecimal percentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryLanguage that)) return false;
        return  Objects.equals(id, that.getId())
                && Objects.equals(country.getName(), that.getCountry().getName())
                && Objects.equals(language, that.getLanguage())
                && Objects.equals(isOfficial, that.getOfficial())
                && Objects.equals(percentage, that.getPercentage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, isOfficial, percentage);
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "country=" + country.getName() +
                ", language='" + language + '\'' +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
    }
}
