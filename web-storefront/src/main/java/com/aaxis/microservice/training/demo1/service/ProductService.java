package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public void initData();

    public List<Product> findProductsByCategoryId(String categoryId);

    public Page<Product> findProductsInPLP(String categoryId, int page, String sortName, String sortValue);

    public Page<Product> searchProducts(int page, String productId, String name, String sortName, String sortValue);

    public void addPriceAndInventory(List<Product> products);

    public double getProductPrice(String pProductId);

    public int getProductInventory(String pProductId);

    public void save(List<Product> pProducts);
}
