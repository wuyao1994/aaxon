package com.aaxis.microservice.training.demo1.service.impl;

import com.aaxis.microservice.training.demo1.dao.InventoryDAO;
import com.aaxis.microservice.training.demo1.domain.Inventory;
import com.aaxis.microservice.training.demo1.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDAO    mInventoryDAO;




    @Override
    public Inventory findInventoryById(String pProductId) {
        Optional<Inventory> optionalInventory = mInventoryDAO.findById(pProductId);
        if (optionalInventory.isPresent()) {
            return optionalInventory.get();
        }
        return null;
    }
}
