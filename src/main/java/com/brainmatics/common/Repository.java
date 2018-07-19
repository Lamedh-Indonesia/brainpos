package com.brainmatics.common;

import java.util.List;

public interface Repository<E> {

    int getCount();
    E getById(int id);
    List<E> getAll();

    void save(E entity);
    void remove(int id);
}
