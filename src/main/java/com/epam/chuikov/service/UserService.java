package com.epam.chuikov.service;

import com.epam.chuikov.entity.User;

public interface UserService {

	public boolean add(User user);

	public boolean exists(User user);

	public boolean existingUserByLogin(String email);

	public User get(String email);
}
