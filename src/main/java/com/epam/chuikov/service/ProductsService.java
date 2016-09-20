package com.epam.chuikov.service;

import java.sql.SQLException;
import java.util.Collection;

import com.epam.chuikov.entity.Category;
import com.epam.chuikov.entity.Manufacturer;
import com.epam.chuikov.entity.Product;
import com.epam.chuikov.form.ProductFilterBean;

public interface ProductsService  {
	int addProduct(Product p)throws SQLException;

	void updateProduct(Product p);

	void removeProduct(int id);

	Product getProduct(int id);

	Collection<Product> getAllProducts();

	Collection<Product> getProductsByFilter(ProductFilterBean filter);

	int getProductsCountWithoutLimit(ProductFilterBean filter);

	Collection<Manufacturer> getAllManufacturers();

	Collection<Category> getAllCategories();
}
