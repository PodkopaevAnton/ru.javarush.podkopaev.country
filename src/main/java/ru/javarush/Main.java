package ru.javarush;

import ru.javarush.service.DataTransformer;
import ru.javarush.service.SqlClient;
import ru.javarush.service.RdsClient;
import ru.javarush.entity.City;
import ru.javarush.redis.CityCountry;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SqlClient sqlClient = new SqlClient();
        RdsClient redisClient = new RdsClient();
        DataTransformer dataTransformer = new DataTransformer();

        List<City> allCities = sqlClient.fetchCityData();
        List<CityCountry> preparedData = dataTransformer.transformCityData(allCities);

        redisClient.pushToRedis(preparedData);
        sqlClient.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        redisClient.getDataById(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        sqlClient.getDataById(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        sqlClient.shutdown();
        redisClient.shutdown();
    }

//    public static void shutdown(SqlClient sqlClient, RdsClient rdsClient) {
//        if (nonNull(sqlClient.getSessionFactory())) {
//            sqlClient.getSessionFactory().close();
//        }
//        if (nonNull(rdsClient.getRedisClient())) {
//            rdsClient.getRedisClient().shutdown();
//        }
//    }
}