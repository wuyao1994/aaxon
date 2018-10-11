package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.dao.CategoryDao;
import com.aaxis.microservice.training.demo1.dao.UserDao;
import com.aaxis.microservice.training.demo1.domain.Category;
import com.aaxis.microservice.training.demo1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao pCategoryDao;

    @Autowired
    private Environment env;


    public void initData(){
        String[] categoryIds = env.getProperty("categoryIds").split(",");

        for(String categoryId : categoryIds){
            if(pCategoryDao.findById(categoryId).isPresent()){
                continue;
            }
            Category category = new Category();
            category.setId(categoryId);
            category.setName("Category_"+categoryId);
            pCategoryDao.save(category);
        }
    }

    public List<Category> findAllCategories(){
        return pCategoryDao.findAll();
    }
}
