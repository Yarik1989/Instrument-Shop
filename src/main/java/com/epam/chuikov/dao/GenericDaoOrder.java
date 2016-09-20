package com.epam.chuikov.dao;

public interface GenericDaoOrder <T, ID> {

    T create(T t);

    T read(ID id);

    boolean update(T t);

    boolean delete(T t);

}