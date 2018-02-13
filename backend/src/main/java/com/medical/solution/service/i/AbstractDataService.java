package com.medical.solution.service.i;


import java.util.List;

public interface AbstractDataService<T> {

    T add(T o);

    T update(T o);

    T getById(long id) throws NoSuchMethodException;

    void delete(long id) throws NoSuchMethodException;

    List<T> getAll();

}
