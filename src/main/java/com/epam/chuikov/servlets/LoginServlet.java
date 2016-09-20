package com.epam.chuikov.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.chuikov.entity.UserBean;
import com.epam.chuikov.service.UserService;
import com.epam.chuikov.utils.RegisterFormValidation;
import com.epam.chuikov.utils.UserInformationExctractor;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInformationExctractor userInformation;
	private RegisterFormValidation validate;
	private UserService service;
	private Map<String, String> errors;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		userInformation = (UserInformationExctractor) config.getServletContext().getAttribute("extractor");
		validate = (RegisterFormValidation) config.getServletContext().getAttribute("userValidate");
		service = (UserService) config.getServletContext().getAttribute("userService");
		errors = new HashMap<>();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			req.setAttribute("bean", session.getAttribute("bean"));
			req.setAttribute("errors", session.getAttribute("errors"));
			session.invalidate();
		}
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean bean = validateForm(req);
		if (errors.size() > 0) {
			HttpSession session = req.getSession();
			// session.setAttribute("bean", bean);
			session.setAttribute("errors", errors);
			/*
			 * resp.sendRedirect("/makita/login");
			 */ } else {
			HttpSession session = req.getSession();
			session.setAttribute("bean", bean);
			// session.setAttribute("errors", errors);

			/*
			 * resp.sendRedirect("/makita/main");
			 */
		}
		resp.sendRedirect("/InstrumentShop/main");

	}

	private UserBean validateForm(HttpServletRequest req) {
		UserBean bean = userInformation.exctractLoginInfo(req);
		errors = validate.isValidLogin(bean);
		String email = bean.getEmail();
		if (errors.size() == 0) {
			if (service.existingUserByLogin(email) == false) {
				validate.setEmailErrorIfUserNotExsist(errors);
				return bean;
			}
			if (!bean.getPassword().equals(service.get(email).getPassword())) {
				validate.setIncorectPassword(errors);

			}

		}
		// service.add(service.get(email));

		bean = service.get(email);

		return bean;
	}
}
