package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.domain.Inventory;

public interface InventoryService {
	public void initData();



	public Inventory findInventoryById(String pProductId);
}
