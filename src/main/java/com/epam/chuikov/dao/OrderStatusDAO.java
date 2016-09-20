package com.epam.chuikov.dao;

import java.util.List;

import com.epam.chuikov.entity.OrderStatus;

public interface OrderStatusDAO extends GenericDaoOrder<OrderStatus, Integer> {
	List<OrderStatus> readAll();
}
