package com.company.endpoints.service;

import com.company.endpoints.model.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequestCounterServiceTest {
    @Autowired
    RequestCounterService requestCounterService;

    private Map<String, Integer> maps;
    private static final String GET = "GET";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final String PATCH = "PATCH";
    private static final String POST = "POST";

    @BeforeEach
    public void init() {
        maps = new HashMap<>();
    }

    @Test
    public void increase_parameterGet() throws NoSuchFieldException, IllegalAccessException {
        requestCounterService.increase(GET);
        requestCounterService.increase(GET);
        requestCounterService.increase(PUT);
        requestCounterService.increase(POST);
        requestCounterService.increase(PATCH);
        maps.put(GET, 2);
        maps.put(PUT, 1);
        maps.put(POST, 1);
        maps.put(PATCH, 1);
        Field field = requestCounterService.getClass().getDeclaredField("stats");
        field.setAccessible(true);
        Object response = field.get(requestCounterService);
        Map<String, Integer> map = (Map<String, Integer>) response;
        Integer actualGet = map.get(GET);
        Integer actualPut = map.get(PUT);
        Integer actualPost = map.get(POST);
        Integer actualPatch = map.get(PATCH);
        Integer expectedGet = maps.get(GET);
        Integer expectedPut = maps.get(PUT);
        Integer expectedPost = maps.get(POST);
        Integer expectedPatch = maps.get(PATCH);
        field.setAccessible(false);
        assertEquals(expectedGet, actualGet);
        assertEquals(expectedPut, actualPut);
        assertEquals(expectedPost, actualPost);
        assertEquals(expectedPatch, actualPatch);
    }

    @Test
    public void getStat_shouldReturnStatisticsList(){
        List<Statistics> expected= new ArrayList<>();
        requestCounterService.increase(GET);
        requestCounterService.increase(DELETE);
        requestCounterService.increase(PUT);
        List<Statistics> actual = requestCounterService.getStat();
        expected.add(new Statistics("GET",1));
        expected.add(new Statistics("DELETE",1));
        expected.add(new Statistics("PUT",1));
        Collections.sort(expected);
        assertEquals(expected,actual);
    }

}