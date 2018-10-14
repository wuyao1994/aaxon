package com.aaxis.microservice.training.demo1.service;

import java.util.List;

import com.aaxis.microservice.training.demo1.domain.Category;

public interface CategoryService {
	public void initData();



	public List<Category> findAllCategories();
}
