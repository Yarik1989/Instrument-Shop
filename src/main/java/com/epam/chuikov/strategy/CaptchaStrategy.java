package com.epam.chuikov.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CaptchaStrategy {
	public abstract int read(HttpServletRequest request);

	public void write(int id, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("captchaId", id);
	}
}
