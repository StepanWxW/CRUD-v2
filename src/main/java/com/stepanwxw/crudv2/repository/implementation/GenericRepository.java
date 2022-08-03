package com.stepanwxw.crudv2.repository.implementation;

import com.stepanwxw.crudv2.exception.NoEntityException;

import java.util.List;

interface GenericRepository<T, L> {
    T create(T t);
    List<T> getAll();
    T getByID(L id);
    T update(T t);
    void remove (L id);
}