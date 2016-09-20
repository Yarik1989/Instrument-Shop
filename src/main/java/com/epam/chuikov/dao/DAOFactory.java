package com.epam.chuikov.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.epam.chuikov.dao.exception.DAOException;

public abstract class DAOFactory {

	private static final String CONFIG = "dao";

	private static DAOFactory instance;
	private static final Logger DAOFACTORYLOGGER = Logger.getLogger(DAOFactory.class);
	private static final String CONNECTION_URL;
	private static final String CONNECTION_USER;
	private static final String CONNECTION_PASSWORD;

	static { // loading connection params from properties
		ResourceBundle resourse = ResourceBundle.getBundle(CONFIG);
		CONNECTION_URL = resourse.getString("CONNECTION_URL");
		CONNECTION_USER = resourse.getString("CONNECTION_USER");
		CONNECTION_PASSWORD = resourse.getString("CONNECTION_PASSWORD");
		// DAOFACTORY = resourse.getString("DAO_FACTORY");

	}

	public static synchronized DAOFactory getInstance() {
		if (instance == null) {

			try {
				Class<?> clazz = Class.forName("com.epam.chuikov.dao.mysql.DAOFactoryMySql");
				instance = (DAOFactory) clazz.newInstance();

			} catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		return instance;
	}

	protected DAOFactory() {
		// no op
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public abstract UserDao getUserDAO();

}
