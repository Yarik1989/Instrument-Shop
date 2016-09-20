package com.epam.chuikov.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.chuikov.dao.DAOFactory;
import com.epam.chuikov.dao.exception.DAOException;

public class TransactionManager {
	private static final Logger LOGGERTM = Logger.getLogger(TransactionManager.class);

	public <T> T doTask(Transaction<T> t) {
		Connection connection = null;
		try {
			connection = DAOFactory.getInstance().getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			T value = t.execute(connection);
			connection.commit();
			return value;
		} catch (SQLException ex) {
			LOGGERTM.error("Cannot do transaction", ex);
			rollBack(connection);
			throw new DAOException("Cannot do transaction", ex);
		} finally {
			commitAndClose(connection);
		}
	}

	public void commitAndClose(Connection connection) {
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException exc) {
				throw new DAOException("Cannot close a connection", exc);
			}
		}
	}

	public void rollBack(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException exc) {

				throw new DAOException("Cannot rollback a connection", exc);
			}
		}
	}

}
