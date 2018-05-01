package edu.jam.telephony.dao;

import java.util.List;

public interface IRepository<T> {

    void add(T object);

    void addAll(List<T> objects);

    T get(int id);

    List<T> getAll();

    int getCount();

}
