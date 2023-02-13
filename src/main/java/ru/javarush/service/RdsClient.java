package ru.javarush.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import ru.javarush.redis.CityCountry;

import java.util.List;

import static java.util.Objects.nonNull;

public class RdsClient implements DataTestService {
    private final RedisClient redisClient;
    private final ObjectMapper mapper;

    public RdsClient(){
        redisClient = prepareRedisClient();
        mapper = new ObjectMapper();
    }

    private RedisClient prepareRedisClient() {
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost",6379));
        try(StatefulRedisConnection<String,String> connection = redisClient.connect()){
            System.out.println("\nConnected to Redis\n");
        }
        return redisClient;
    }

    public void pushToRedis(List<CityCountry> preparedData) {
        try (StatefulRedisConnection<String,String>connection = redisClient.connect()){
            RedisStringCommands<String,String> sync = connection.sync();
            for (CityCountry cityCountry : preparedData){
                try {
                    sync.set(String.valueOf(cityCountry.getId()),mapper.writeValueAsString(cityCountry));
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void getDataById(List<Integer> ids){
        try (StatefulRedisConnection<String,String> connection = redisClient.connect()){
            RedisStringCommands<String,String> sync = connection.sync();
            for (Integer id : ids){
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        if (nonNull(redisClient)) {
            redisClient.close();
        }
    }
}
