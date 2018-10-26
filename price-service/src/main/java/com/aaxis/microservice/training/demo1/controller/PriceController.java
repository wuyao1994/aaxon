package com.aaxis.microservice.training.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxis.microservice.training.demo1.domain.ItemPrice;
import com.aaxis.microservice.training.demo1.service.ItemPriceService;

@RestController
@RequestMapping("price")
public class PriceController {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(PriceController.class);
	@Autowired
	private ItemPriceService	mItemPriceService;



	@Cacheable(value = "price", key = "#productId")
	@GetMapping("/{productId}")
	public ItemPrice findPrice(@PathVariable("productId") String productId) {
		LOGGER.info("find price by product id:{}", productId);
		return mItemPriceService.findItemPriceById(productId);
	}
}
