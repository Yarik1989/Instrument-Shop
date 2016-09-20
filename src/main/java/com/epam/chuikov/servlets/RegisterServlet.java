package com.epam.chuikov.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.chuikov.entity.User;
import com.epam.chuikov.entity.UserBean;
import com.epam.chuikov.service.CaptchaService;
import com.epam.chuikov.service.UserService;
import com.epam.chuikov.strategy.CaptchaStrategy;
import com.epam.chuikov.utils.RegisterFormValidation;
import com.epam.chuikov.utils.UserCreator;
import com.epam.chuikov.utils.UserInformationExctractor;

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInformationExctractor userInformation;
	private RegisterFormValidation validate;
	// private IService<User> service;
	private UserService service;
	private CaptchaService captchaService;
	private Map<String, String> errors;
	private UserCreator userCreator;
	private CaptchaStrategy strategy;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		userInformation = (UserInformationExctractor) config.getServletContext().getAttribute("extractor");
		validate = (RegisterFormValidation) config.getServletContext().getAttribute("userValidate");
		service = (UserService) config.getServletContext().getAttribute("userService");
		captchaService = (CaptchaService) config.getServletContext().getAttribute("captchaService");
		userCreator = (UserCreator) config.getServletContext().getAttribute("userCreator");
		strategy = (CaptchaStrategy) getServletContext().getAttribute("strategy");
		errors = new HashMap<>();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			req.setAttribute("bean", session.getAttribute("bean"));
			req.setAttribute("errors", session.getAttribute("errors"));
			session.invalidate();
		}
		String captcha = captchaService.generateString();
		int id = captchaService.insert(captcha);
		strategy.write(id, req, resp);
		req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean bean = validateForm(req);
		if (errors.size() > 0) {
			HttpSession session = req.getSession();
			session.setAttribute("bean", bean);
			session.setAttribute("errors", errors);
			resp.sendRedirect("/InstrumentShop/register");
		} else {
			resp.sendRedirect("/InstrumentShop/main");
		}
	}

	private UserBean validateForm(HttpServletRequest req) {
		UserBean bean = userInformation.exctract(req);
		errors = validate.isValid(bean);
		int id = strategy.read(req);
		validate.captchaCheck(bean, captchaService.select(id), errors);
		User user = userCreator.createUser(bean);
		if (service.exists(user) == true) {
			validate.setEmailErrorIfUserExsist(errors);

		}
		if (errors.size() == 0) {
			service.add(user);
		}

		return bean;
	}

}
