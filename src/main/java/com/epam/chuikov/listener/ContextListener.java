package com.epam.chuikov.listener;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.chuikov.dao.CategoryDao;
import com.epam.chuikov.dao.ManufacturerDao;
import com.epam.chuikov.dao.ProductDao;
import com.epam.chuikov.dao.UserDao;
import com.epam.chuikov.dao.mysql.CategoryDaoMySql;
import com.epam.chuikov.dao.mysql.ManufacturerDaoMySql;
import com.epam.chuikov.dao.mysql.ProductDaoMySql;
import com.epam.chuikov.dao.mysql.UserDaoMySql;
import com.epam.chuikov.service.CaptchaService;
import com.epam.chuikov.service.ProductsService;
import com.epam.chuikov.service.UserService;
import com.epam.chuikov.service.impl.ProductsServiceDaoImpl;
import com.epam.chuikov.service.impl.UserServiceImpl;
import com.epam.chuikov.storage.CaptchaStorages;
import com.epam.chuikov.strategy.CaptchaStrategy;
import com.epam.chuikov.strategy.StrategyFactory;
import com.epam.chuikov.transaction.TransactionManager;
import com.epam.chuikov.util.img.ImgProvider;
import com.epam.chuikov.util.img.JpegImgProvider;
import com.epam.chuikov.utils.BufferedImageCreator;
import com.epam.chuikov.utils.RegisterFormValidation;
import com.epam.chuikov.utils.UserCreator;
import com.epam.chuikov.utils.UserInformationExctractor;

public class ContextListener implements ServletContextListener {

	private long default_timeout;
	private String captchaDICT;
	private int captchaLength;
	private CaptchaStrategy strategy;
	private CaptchaService captchaService;
	public static final String INIT_PRODUCTS_SERVICE_KEY = "init:productService";
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	public static final String PARAM_PRODUCT_IMAGE_FOLDER = "productImagesFolder";
	public static final String INIT_PRODUCT_IMAGES_PROVIDER_KEY = "init:prodImagesProvider";

	private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	});

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		initContextParameters(context);
		initStorage(context);
		initService(context);
		initProductImageProvider(context);

		initClearCaptcha();
	}

	private void initClearCaptcha() {
		scheduler.scheduleWithFixedDelay((Runnable) () -> {
			long current = System.currentTimeMillis();
			captchaService.keySet().stream().filter(k -> !k.isLive(current)).forEach(k -> captchaService.remove(k));
		}, 1, default_timeout / 5, TimeUnit.SECONDS);
	}

	private void initService(ServletContext context) {
		context.setAttribute("captchaService", captchaService);
		context.setAttribute("userService", new UserServiceImpl());
		context.setAttribute("imageCreator", new BufferedImageCreator());
		context.setAttribute("userValidate", new RegisterFormValidation());
		context.setAttribute("extractor", new UserInformationExctractor());
		context.setAttribute("userCreator", new UserCreator());
		context.setAttribute("strategy", strategy);
		/*
		 * DataSource ds = null; try { InitialContext initialContext = new
		 * InitialContext(); ds =
		 * (DataSource)initialContext.lookup("java:comp/env/jdbc/instrumentshop"
		 * ); if (ds == null) { throw new IllegalArgumentException(
		 * "data source is not initialized"); } } catch (NamingException e) {
		 * throw new IllegalArgumentException(e); }
		 */
		TransactionManager tranManager = new TransactionManager();

		UserDao userDao = new UserDaoMySql();
		UserService userService = new UserServiceImpl();
		context.setAttribute(INIT_USER_SERVICE_KEY, userService);

		ProductDao productDao = new ProductDaoMySql();
		CategoryDao categoryDao = new CategoryDaoMySql();
		ManufacturerDao manufacturerDao = new ManufacturerDaoMySql();
		ProductsService productService = new ProductsServiceDaoImpl(tranManager, productDao, categoryDao,
				manufacturerDao);
		context.setAttribute(INIT_PRODUCTS_SERVICE_KEY, productService);

	}

	private void initContextParameters(ServletContext context) {
		strategy = StrategyFactory.createStrategy(context.getInitParameter("tagStrategy"));
		captchaDICT = context.getInitParameter("captchaDICT");
		captchaLength = Integer.parseInt(context.getInitParameter("captchaLength"));
	}

	private void initStorage(ServletContext context) {
		default_timeout = Long.parseLong(context.getInitParameter("timeOutCaptcha"));
		CaptchaStorages.default_timeout = default_timeout;
		captchaService = new CaptchaService(captchaDICT, captchaLength);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		scheduler.shutdown();
	}

	private void initProductImageProvider(ServletContext context) {
		String productImagesFolderPath = context.getInitParameter(PARAM_PRODUCT_IMAGE_FOLDER);

		if (productImagesFolderPath == null || productImagesFolderPath.isEmpty()) {
			throw new IllegalArgumentException(
					"parameter '" + PARAM_PRODUCT_IMAGE_FOLDER + "' must specify folder to contain product images");
		}

		File folder = new File(productImagesFolderPath);
		if (!folder.exists() || !folder.isDirectory()) {
			if (!folder.mkdirs()) {
				throw new IllegalArgumentException("folder specified in parameter '" + PARAM_PRODUCT_IMAGE_FOLDER
						+ "' = [" + productImagesFolderPath + "] does not exist and can not be created");
			}
		}

		ImgProvider prodImgProvider = new JpegImgProvider(productImagesFolderPath);
		context.setAttribute(INIT_PRODUCT_IMAGES_PROVIDER_KEY, prodImgProvider);

	}
}
