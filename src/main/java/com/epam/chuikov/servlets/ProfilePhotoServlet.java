package com.epam.chuikov.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.chuikov.entity.User;

@WebServlet("/photoLoader")
public class ProfilePhotoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8148894221495349774L;
	public static final Logger LOG = Logger.getLogger(ProfilePhotoServlet.class);
	private String repositoryPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initRepositoryPath();
	}

	private void initRepositoryPath() {
		this.repositoryPath = getServletContext().getInitParameter("repositoryPath");
		if (repositoryPath == null) {
			LOG.error("RepositoryPath cannot be initialized");
			throw new IllegalArgumentException("RepositoryPath cannot be initialized");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User authUser = (User) req.getSession().getAttribute("bean");
		FileInputStream fis;
		File userPhoto = new File(authUser.getPhotoPath());
		if (!userPhoto.exists()) {
			userPhoto = new File(repositoryPath + "default.jpg");
		}
		fis = new FileInputStream(userPhoto);
		byte[] photo = new byte[fis.available()];
		fis.read(photo);
		OutputStream os = resp.getOutputStream();
		os.write(photo);
		os.close();
		fis.close();
	}
}
