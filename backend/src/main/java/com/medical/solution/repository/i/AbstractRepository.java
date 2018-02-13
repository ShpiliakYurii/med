package com.medical.solution.repository.i;

import java.util.List;

public interface AbstractRepository<T> {

    List<T> findAll();

    T findById(long id);

    T create(final T object);

    T update(final T object);

    void delete(long id);

}
