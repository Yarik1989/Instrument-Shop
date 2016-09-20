package com.epam.chuikov.dao.exception;


public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(String mes, Exception ex) {
		super(mes, ex);
	}

}
