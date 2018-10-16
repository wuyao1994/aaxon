package com.aaxis.microservice.training.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxis.microservice.training.demo1.domain.Inventory;
import com.aaxis.microservice.training.demo1.service.InventoryService;

@RestController
@RequestMapping("inventory")
public class InventoryController {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(InventoryController.class);
	@Autowired
	private InventoryService	mInventoryService;



	@GetMapping("/initData")
	public void initData() {
		mInventoryService.initData();
	}


	@Cacheable(value = "category", key = "#productId")
	@GetMapping("/{productId}")
	public Inventory findPrice(@PathVariable("productId") String productId) {
		LOGGER.info("find inventory by product id:{}", productId);
		return mInventoryService.findInventoryById(productId);
	}
}
