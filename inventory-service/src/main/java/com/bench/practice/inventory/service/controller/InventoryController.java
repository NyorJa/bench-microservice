package com.bench.practice.inventory.service.controller;

import com.bench.practice.inventory.service.exception.ResourceNotFoundException;
import com.bench.practice.inventory.service.model.Inventory;
import com.bench.practice.inventory.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @GetMapping("/{skuCode}")
    public Boolean isInStock(@PathVariable String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                                                 .orElseThrow(() -> new ResourceNotFoundException("Cannot find product by sku code " + skuCode));
        return inventory.getStock() > 0;
    }
}
