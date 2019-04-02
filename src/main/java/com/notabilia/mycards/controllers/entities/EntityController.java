package com.notabilia.mycards.controllers.entities;

import org.springframework.http.ResponseEntity;

interface EntityController<T, ID> {

    ResponseEntity<Iterable<T>> getAll();
    ResponseEntity<T> getOne(ID id);
    ResponseEntity<T> createOne(T entity);
}
