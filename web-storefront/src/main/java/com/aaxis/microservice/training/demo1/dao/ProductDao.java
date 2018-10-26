package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDao extends ElasticsearchRepository<Product, String> {
    public List<Product> findProductsByCategoryId(String categoryId);
}
