package com.epam.chuikov.service.impl;

import java.sql.Connection;

import com.epam.chuikov.dao.DAOFactory;
import com.epam.chuikov.dao.UserDao;
import com.epam.chuikov.entity.User;
import com.epam.chuikov.service.UserService;
import com.epam.chuikov.transaction.Transaction;
import com.epam.chuikov.transaction.TransactionManager;

public class UserServiceImpl implements UserService {

	private TransactionManager transactionManager;

	private DAOFactory daoFactory;

	private UserDao userDAO;
	public static User user = new User();

	public UserServiceImpl() {
		daoFactory = DAOFactory.getInstance();
		userDAO = daoFactory.getUserDAO();
		transactionManager = new TransactionManager();
	}

	@Override
	public boolean add(final User user) {
		boolean result = transactionManager.doTask(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection con) {
				return userDAO.create(con, user);
			}
		});
		return result;
	}

	@Override
	public boolean exists(User user) {
		String email = user.getEmail();
		boolean result = transactionManager.doTask(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection con) {
				User exsistUser = userDAO.getUserByEmail(con, email);
				if (exsistUser == null) {
					return false;
				}
				return true;
			}
		});

		return false;
	}

	@Override
	public boolean existingUserByLogin(String email) {
		boolean result = transactionManager.doTask(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection con) {
				User exsistUser = userDAO.getUserByEmail(con, email);
				if (exsistUser == null) {

					return false;
				}
				return true;
			}
		});
		return result;
	}

	@Override
	public User get(String email) {
		boolean result = transactionManager.doTask(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection con) {
				User exsistUser = userDAO.getUserByEmail(con, email);
				if (exsistUser == null) {

					return false;
				}
				user = exsistUser;
				return true;
			}
		});
		return user;
	}

}
