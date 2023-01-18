package com.regpet.api.interfaces;

import java.util.List;
import java.util.UUID;

public interface ICrudMethods<T> {

    T add(T object);
    T getById(UUID id);
    List<T> getAll();
    T update(UUID id, T object);
    void delete(UUID id);
}
