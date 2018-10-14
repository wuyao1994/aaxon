package com.aaxis.microservice.training.demo1.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.aaxis.microservice.training.demo1.dao.CategoryDao;
import com.aaxis.microservice.training.demo1.domain.Category;
import com.aaxis.microservice.training.demo1.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryDao			pCategoryDao;

	@Autowired
	private Environment			env;



	@Override
	public void initData() {
		String[] categoryIds = env.getProperty("categoryIds").split(",");

		for (String categoryId : categoryIds) {
			if (pCategoryDao.findById(categoryId).isPresent()) {
				continue;
			}
			Category category = new Category();
			category.setId(categoryId);
			category.setName("Category_" + categoryId);
			pCategoryDao.save(category);
		}
	}



	@Override
	public List<Category> findAllCategories() {
		return pCategoryDao.findAll();
	}
}
