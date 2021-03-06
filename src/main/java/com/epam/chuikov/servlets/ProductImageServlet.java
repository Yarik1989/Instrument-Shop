package com.epam.chuikov.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.chuikov.entity.Product;
import com.epam.chuikov.service.ProductsService;
import com.epam.chuikov.util.img.ImageInfo;
import com.epam.chuikov.util.img.ImgProvider;


@WebServlet("/productImage")
public class ProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String PRODUCT_ID_PARAM = "prodId";
	public static final int CACHE_TIME_SECONDS = 600;
	
	private ImgProvider prodImageProvider;
	private ProductsService productsService;
	
	@Override
	public void init() throws ServletException {
		prodImageProvider = (ImgProvider)getServletContext().getAttribute("init:prodImagesProvider");
		productsService = (ProductsService)getServletContext().getAttribute("init:productService");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String prodId = req.getParameter(PRODUCT_ID_PARAM);
		int id = 0;
		if (prodId != null && !prodId.isEmpty()) {
			try {
				id = Integer.parseInt(prodId);
			} catch (NumberFormatException e) {
					return;
			}
		}
		Product product = productsService.getProduct(id);
		if (product != null && product.getImgFile() != null) {
			ImageInfo prodInfo = prodImageProvider.read(product.getImgFile());
			resp.setContentType(prodInfo.getMimeType());
			resp.setHeader("Cache-Control", "public, max-age=" + CACHE_TIME_SECONDS);
			prodImageProvider.write(prodInfo.getImage(), resp.getOutputStream());
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
