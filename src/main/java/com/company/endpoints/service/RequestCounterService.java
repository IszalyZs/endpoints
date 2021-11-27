package com.company.endpoints.service;

import com.company.endpoints.model.Statistics;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestCounterService {
    private final Map<String, Integer> stats = new TreeMap<>();

    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String PATCH = "PATCH";
    public static final String POST = "POST";

    public void increase(String endpoint) {
        if (stats.containsKey(endpoint)) {
            stats.put(endpoint, stats.get(endpoint) + 1);
        } else stats.put(endpoint, 1);
    }

    public List<Statistics> getStat() {
        List<Statistics> statistics = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            statistics.add(new Statistics(entry.getKey(), entry.getValue()));
        }
        System.out.println(stats.size());
        return statistics;
    }


}
