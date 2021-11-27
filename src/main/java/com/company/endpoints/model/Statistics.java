package com.company.endpoints.model;

import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

@Data
public class Statistics implements Comparable<Statistics>{
    private String method;
    private Integer usage;

    public Statistics(String method, Integer usage) {
        this.method = method;
        this.usage = usage;
    }

    Comparator<Statistics> comparator= Comparator.comparing(Statistics::getMethod);

    @Override
    public int compareTo(Statistics other) {
        return comparator.compare(this,other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method);
    }
}
