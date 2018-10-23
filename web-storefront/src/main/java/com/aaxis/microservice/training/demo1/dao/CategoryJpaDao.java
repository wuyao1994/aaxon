package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.CategoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaDao extends JpaRepository<CategoryJpa, String> {
}
