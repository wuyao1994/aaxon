package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Inventory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface InventoryDAO extends ElasticsearchRepository<Inventory,String> {
}
