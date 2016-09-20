package com.epam.chuikov.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.chuikov.entity.User;

public interface UserDao extends GenericDAO<User> {

	public User getUserByEmail(Connection con, String email); 

}
