package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Product;
import com.aaxis.microservice.training.demo1.domain.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaDao extends JpaRepository<ProductJpa, String> {
    public List<ProductJpa> findProductsByCategory_Id(String categoryId);
}
