package com.epam.chuikov.dao;

import java.util.List;

import com.epam.chuikov.entity.User;


public interface UserDaoForCart extends GenericDaoOrder<User, Integer> {
    List<User> readAll();

    User read(String email);
}
