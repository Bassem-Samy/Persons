package com.bassem.persons.database;

import com.bassem.persons.database.models.Person;

import java.util.List;

/**
 * Created by Bassem on 7/1/2017.
 */

public interface BasicTableOperations<T> {

    void create(T item);

    int update(T item);

    void delete(T item);

    void insertAll(List<T> items);

    void deleteAll();

    List<T> getAll();

    T getRecordById(String id);
}
