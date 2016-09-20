package com.epam.chuikov.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.epam.chuikov.entity.UserBean;

public class UserInformationExctractor {
	public UserBean exctractLoginInfo(HttpServletRequest request) {
		UserBean user = new UserBean();
		exctractLoginAndPassword(request, user);
		return user;
	}

	private void exctractLoginAndPassword(HttpServletRequest request, UserBean user) {
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));

	}

	public UserBean exctract(HttpServletRequest request) {
		UserBean user = new UserBean();
		exctractMainInfo(request, user);
		exctractSecondaryInfo(request, user);
		user.setPhotoPath(getImage(request, user.getEmail()));
		return user;
	}

	private void exctractSecondaryInfo(HttpServletRequest request, UserBean user) {
		user.setRepeatPassword(request.getParameter("repeatPassword"));
		String sub = request.getParameter("subscribe");
		String captcha = request.getParameter("captchaValue");
		user.setCaptcha(captcha);
		if (sub != null)
			user.setSubscribe(true);
	}

	private void exctractMainInfo(HttpServletRequest request, UserBean user) {
		
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
	}

	private String getImage(HttpServletRequest req, String userEmail) {
		try {
			Part filePart = req.getPart("file");
			InputStream fileContent = filePart.getInputStream();
			if (fileContent.available() > 0) {
				File uploads = new File("C:\\Users\\Yaroslav_Chuikov\\newworkspace\\repository");
				File image = new File(uploads, userEmail + ".jpg");
				try {
					Files.copy(fileContent, image.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException ex) {
					System.err.println(
							this.getClass().getName() + "==> error while loading the photo" + " " + ex.getMessage());
				}
				return image.getCanonicalPath();
			}

		} catch (IOException | ServletException ex) {
			System.err.println("Error while copying photoFile");
		}
		return "";
	}
}
