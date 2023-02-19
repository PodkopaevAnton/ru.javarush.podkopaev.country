package ru.javarush.country;

import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.service.DataTransformer;
import ru.javarush.country.service.SqlService;
import ru.javarush.country.service.RedisService;
import ru.javarush.country.entity.City;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        SqlService sqlService = new SqlService();
        RedisService redisService = new RedisService();
        DataTransformer dataTransformer = new DataTransformer();

        List<City> allCities = sqlService.fetchCityData();
        List<CityCountry> preparedData = dataTransformer.transformCityData(allCities);

        redisService.pushToRedis(preparedData);
        sqlService.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisService.getDataById(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        sqlService.getDataById(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        sqlService.shutdown();
        redisService.shutdown();
    }
}