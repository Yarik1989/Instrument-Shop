package com.epam.chuikov.utils;

import com.epam.chuikov.entity.User;
import com.epam.chuikov.entity.UserBean;

public class UserCreator {

    public User createUser(UserBean bean) {

	User user = new User();
	user.setFirstName(bean.getFirstName());
	user.setLastName(bean.getLastName());
	user.setEmail(bean.getEmail());
	user.setPassword(bean.getPassword());
	user.setSubscribe(bean.isSubscribe());
	user.setPhotoPath(bean.getPhotoPath());
	return user;
    }
}
