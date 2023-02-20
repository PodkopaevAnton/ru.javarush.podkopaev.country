package ru.javarush.country.redis;

import ru.javarush.country.entity.CountryLanguage;
import java.math.BigDecimal;
import java.util.Objects;


public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return Objects.equals(language, language1.getLanguage())
                && Objects.equals(isOfficial, language1.getOfficial())
                && Objects.equals(percentage, language1.getPercentage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, isOfficial, percentage);
    }

    @Override
    public String toString() {
        return "Language{" +
                "language='" + language + '\'' +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
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

    public void setCountryLanguage(CountryLanguage countryLanguage){
        setLanguage(countryLanguage.getLanguage());
        setOfficial(countryLanguage.getOfficial());
        setPercentage(countryLanguage.getPercentage());
    }
}
