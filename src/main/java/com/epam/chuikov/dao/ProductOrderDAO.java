package com.epam.chuikov.dao;

import java.sql.SQLException;

import com.epam.chuikov.entity.ProductOrder;

public interface ProductOrderDAO extends GenericDaoOrder<ProductOrder, Integer> {
    ProductOrder read(Integer product_id, Integer order_id) throws SQLException;
}
