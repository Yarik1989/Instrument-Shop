package com.epam.chuikov.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.chuikov.entity.Category;

public interface CategoryDao {
	int add(Category c, Connection con) throws SQLException;
	Category get(int id, Connection con) throws SQLException;
	void update(Category c, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	Collection<Category> getAll(Connection con) throws SQLException;
}
