package ru.javarush.country.service;

import java.util.List;

public interface DataTestService {
    void getDataById(List<Integer> ids);

    void shutdown();
}
