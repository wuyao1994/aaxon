package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Inventory;
import com.aaxis.microservice.training.demo1.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService mInventoryService;

    @GetMapping("/initData")
    public void initData() {
        mInventoryService.initData();
    }

    @GetMapping("/{productId}")
    public Inventory findPrice(@PathVariable("productId") String productId) {
        return mInventoryService.findInventoryById(productId);
    }
}
