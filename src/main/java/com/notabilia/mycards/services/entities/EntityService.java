package com.notabilia.mycards.services.entities;

interface EntityService<T, ID> {

    Iterable<T> getAll();
    T getOne(ID id);
    T createOne(T entity);
}
