package com.epam.chuikov.service;

import java.util.List;

public interface IService<T> {
	T insert(T entity);

	T update(T entity);

	T delete(T entity);

	T delete(int id);

	T select(int id);

	T select(T entity);

	List<T> selectAll();
}
