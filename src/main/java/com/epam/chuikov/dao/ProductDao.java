package com.epam.chuikov.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.chuikov.entity.Product;
import com.epam.chuikov.form.ProductFilterBean;

public interface ProductDao {
	int add(Product p, Connection con) throws SQLException;

	void update(Product p, Connection con) throws SQLException;

	void remove(int id, Connection con) throws SQLException;

	Product get(int id, Connection con) throws SQLException;

	Collection<Product> getAll(Connection con) throws SQLException;

	Collection<Product> getByFilter(ProductFilterBean filter, Connection con) throws SQLException;

	int getCountWithoutLimit(ProductFilterBean filter, Connection con) throws SQLException;
}
