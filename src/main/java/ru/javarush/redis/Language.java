package ru.javarush.redis;

import ru.javarush.entity.CountryLanguage;

import java.math.BigDecimal;

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
