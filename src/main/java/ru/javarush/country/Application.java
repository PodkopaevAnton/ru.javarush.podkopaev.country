package ru.javarush.country;

import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.service.DataTransformer;
import ru.javarush.country.service.SqlTestService;
import ru.javarush.country.service.RedisTestService;
import ru.javarush.country.entity.City;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        SqlTestService sqlTestService = new SqlTestService();
        RedisTestService redisTestService = new RedisTestService();
        DataTransformer dataTransformer = new DataTransformer();

        List<City> allCities = sqlTestService.fetchCityData();
        List<CityCountry> preparedData = dataTransformer.transformCityData(allCities);

        redisTestService.pushToRedis(preparedData);
        sqlTestService.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisTestService.getDataById(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        sqlTestService.getDataById(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        sqlTestService.shutdown();
        redisTestService.shutdown();
    }
}