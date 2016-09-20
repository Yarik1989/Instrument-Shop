package com.epam.chuikov.dao.mysql;

import com.epam.chuikov.dao.DAOFactory;
import com.epam.chuikov.dao.UserDao;

public class DAOFactoryMySql extends DAOFactory {

	@Override
	public UserDao getUserDAO() {
		return new UserDaoMySql();

	}
}
