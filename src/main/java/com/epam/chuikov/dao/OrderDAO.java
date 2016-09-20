package com.epam.chuikov.dao;


import java.util.List;

import com.epam.chuikov.entity.Order;


public interface OrderDAO extends GenericDaoOrder<Order, Integer> {
    List<Order> readAll(Integer user_id);
}
