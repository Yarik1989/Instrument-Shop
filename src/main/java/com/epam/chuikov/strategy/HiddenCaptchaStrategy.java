package com.epam.chuikov.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenCaptchaStrategy extends CaptchaStrategy {

	@Override
	public int read(HttpServletRequest request) {
		String id = request.getParameter("captchaId");
		return Integer.parseInt(id);
	}

	@Override
	public void write(int id, HttpServletRequest request, HttpServletResponse response) {
		super.write(id, request, response);
		request.setAttribute("strategy", "hidden");
	}
}
