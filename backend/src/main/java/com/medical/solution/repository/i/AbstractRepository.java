package com.medical.solution.repository.i;

import java.util.List;

public interface AbstractRepository<T> {

    List<T> findAll();

    T findById(long id);

    T create(final T objectType);

    T update(final T objectType);

    void delete(long id);

    List<T> findAllByParentId(Long id);

}
