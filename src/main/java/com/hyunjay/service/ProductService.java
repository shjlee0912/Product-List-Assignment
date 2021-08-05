package com.hyunjay.service;

import java.util.List;

import com.hyunjay.model.Product;

public interface ProductService {
	void create(Product prd);
	Product getById(Long id) throws Exception;
	void update(Long id, Product prd);
	void deleteById(Long id);
	List<Product> getAllProducts();
}
