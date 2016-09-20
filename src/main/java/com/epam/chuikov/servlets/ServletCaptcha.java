package com.epam.chuikov.servlets;

import com.epam.chuikov.service.CaptchaService;
import com.epam.chuikov.strategy.CaptchaStrategy;
import com.epam.chuikov.utils.BufferedImageCreator;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cap")
public class ServletCaptcha extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4135386086995177473L;
	private CaptchaService captchaService;
	private BufferedImageCreator bufferedImage;
	private CaptchaStrategy strategy;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		captchaService = (CaptchaService) config.getServletContext().getAttribute("captchaService");
		bufferedImage = (BufferedImageCreator) getServletContext().getAttribute("imageCreator");
		strategy = (CaptchaStrategy) getServletContext().getAttribute("strategy");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = strategy.read(request);
		String captcha = captchaService.select(id);
		response.setContentType("image/gif");
		ImageIO.write(bufferedImage.create(captcha), "gif", response.getOutputStream());
	}
}
