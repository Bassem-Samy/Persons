package com.bassem.persons.database;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface BasicTableOperations<T> {

    void create(T item);

    int update(T item);

    void delete(T item);

    void insertAll(List<T> items);
}
