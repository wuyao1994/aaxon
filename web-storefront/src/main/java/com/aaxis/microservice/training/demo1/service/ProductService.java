package com.aaxis.microservice.training.demo1.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aaxis.microservice.training.demo1.domain.Product;

public interface ProductService {
	public void initData();



	public List<Product> findProductsByCategoryId(String categoryId);



	public Page<Product> findProductsInPLP(String categoryId, int page, String sortName, String sortValue);



	public Page<Product> searchProducts(int page, String productId, String name, String sortName, String sortValue);



	public void addPriceAndInventory(List<Product> products);



	public double getProductPrice(String pProductId);



	public int getProductInventory(String pProductId);

}
