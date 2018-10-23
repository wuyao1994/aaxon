package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.domain.Category;

import java.util.List;

public interface CategoryService {
    public void initData();

    public List<Category> findAllCategories();

    public void save(List<Category> pCategories);
}
