package com.aaxon.service;

import com.aaxon.domain.Inventory;

public interface InventoryService {
	public void initData();



	public Inventory findInventoryById(String pProductId);
}
