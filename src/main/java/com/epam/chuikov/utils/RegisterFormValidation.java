package com.epam.chuikov.utils;

import java.util.HashMap;
import java.util.Map;

import com.epam.chuikov.entity.UserBean;

public class RegisterFormValidation {
	private static final String NAME_REG_EX = "^[A-Z][a-z]{2,19}$";
	private static final String EMAIL_REG_EX = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,4}$";
	private static final String PASSWORD_REG_EX = "^[a-zA-Z0-9]{3,30}";
	private static final String NAME_ERROR = "The length must be between 2 and 20 latin characters with leading uppercase.";
	private static final String EMAIL_ERROR = "Wrong email format (example@mail.com).";
	private static final String PASSWORD_REG_EX_ERROR = "Password should contain at least one digit, lower case, upper case letter and be at least 8 symbols long.";
	private static final String PASSWORD_EQUALS_ERROR = "Passwords do not match.";
	private static final String CAPTCHA_ERROR = "Captcha Error!!!";
	private static final String USER_EXSIST_ERROR = "Users with this email already exsists!!!Please login...";
	private static final String USER_NOT_EXSIST_ERROR = "Users with this email doesn't exsists!!!Please register...";
	private static final String INCORRECT_PASSWORD_ERROR = "Password is incorrect please try again.";

	public Map<String, String> isLoginFormValid(UserBean bean) {
		Map<String, String> errors = new HashMap();
		emailValid(bean, errors);
		passwordValid(bean, errors);
		return errors;
	}

	public Map<String, String> isValidLogin(UserBean bean) {
		Map<String, String> errors = new HashMap();
		emailValid(bean, errors);
		passwordValid(bean, errors);
		return errors;
	}

	public Map<String, String> isValid(UserBean bean) {
		Map<String, String> errors = new HashMap();
		firstNameValid(bean, errors);
		lastNameValid(bean, errors);
		emailValid(bean, errors);
		passwordValid(bean, errors);
		repeatPasswordValid(bean, errors);
		
		return errors;
	}

	public void captchaCheck(UserBean bean, String captcha, Map<String, String> errors) {
		if (!bean.getCaptcha().equals(captcha)) {
			errors.put("captcha", CAPTCHA_ERROR);
		}
	}

	public void setEmailErrorIfUserExsist(Map<String, String> errors) {
		errors.put("email", USER_EXSIST_ERROR);
	}
	/*
	 * public boolean isRegisterNewUser(User user, Map<String, String> errors) {
	 * if (user == null) { errors.put("email",
	 * "Users with this email already exsists."); return false; }
	 * 
	 * return true; }
	 */

	private void repeatPasswordValid(UserBean bean, Map<String, String> errors) {
		if (!bean.getRepeatPassword().matches(PASSWORD_REG_EX)
				&& !bean.getRepeatPassword().equals(bean.getPassword())) {
			errors.put("repeatPassword", PASSWORD_EQUALS_ERROR);
		}
	}

	private void passwordValid(UserBean bean, Map<String, String> errors) {
		if (!bean.getPassword().matches(PASSWORD_REG_EX)) {
			errors.put("password", PASSWORD_REG_EX_ERROR);
		}
	}

	private void emailValid(UserBean bean, Map<String, String> errors) {
		if (!bean.getEmail().matches(EMAIL_REG_EX)) {
			errors.put("email", EMAIL_ERROR);
		}
	}

	private void lastNameValid(UserBean bean, Map<String, String> errors) {
		if (!bean.getLastName().matches(NAME_REG_EX)) {
			errors.put("lastName", NAME_ERROR);
		}
	}

	private void firstNameValid(UserBean bean, Map<String, String> errors) {
		if (!bean.getFirstName().matches(NAME_REG_EX)) {
			errors.put("firstName", NAME_ERROR);
		}
	}

	public void setEmailErrorIfUserNotExsist(Map<String, String> errors) {
		errors.put("email", USER_NOT_EXSIST_ERROR);
	}

	public void setIncorectPassword(Map<String, String> errors) {
		errors.put("password", INCORRECT_PASSWORD_ERROR);
	}

}
