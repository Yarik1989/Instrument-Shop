package com.epam.chuikov.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public boolean create(Connection con, T t);
	
	public T getById(Connection con, long id);
	
	public boolean update(Connection con, T t);
	
	public boolean delete(Connection con, long id);
	
	public List<T> getAll(Connection con) throws SQLException;


}
