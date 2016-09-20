package com.epam.chuikov.dao;

import java.util.List;

import com.epam.chuikov.dto.FilterParameter;
import com.epam.chuikov.entity.Product;

public interface ProductsDAO extends GenericDaoOrder<Product, Integer>{
    List<Product> readAll();
    List<Product> getFiltered(FilterParameter filterParameter);
    int getFilteredCount(FilterParameter filterParameter);
}
