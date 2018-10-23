package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryDao extends ElasticsearchRepository<Category, String> {
}
