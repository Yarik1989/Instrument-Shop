package com.epam.chuikov.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction<T> {
	
	T execute(Connection con) throws SQLException;

}