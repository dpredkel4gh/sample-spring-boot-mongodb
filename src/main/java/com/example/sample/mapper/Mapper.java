package com.example.sample.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T, F> {

    T map(F source);

    default List<T> map(Collection<F> collection) {
        return collection.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
