package com.aaxon.service.impl;

import com.aaxon.dao.InventoryDAO;
import com.aaxon.domain.Inventory;
import com.aaxon.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDAO mInventoryDAO;



	@Override
	public void initData() {
		mInventoryDAO.addItemInventory();
	}



	@Override
	public Inventory findInventoryById(String pProductId) {
		Optional<Inventory> optionalInventory = mInventoryDAO.findById(pProductId);
		if (optionalInventory.isPresent()) {
			return optionalInventory.get();
		}
		return null;
	}
}
