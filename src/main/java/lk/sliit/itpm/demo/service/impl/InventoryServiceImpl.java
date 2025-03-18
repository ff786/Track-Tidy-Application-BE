package lk.sliit.itpm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.sliit.itpm.demo.document.Inventory;
import lk.sliit.itpm.demo.dto.InventoryDTO;
import lk.sliit.itpm.demo.repository.InventoryRepository;
import lk.sliit.itpm.demo.service.InventoryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public InventoryDTO createProduct(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setUserId(inventoryDTO.getUserId());
        inventory.setProductName(inventoryDTO.getProductName());
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setProductValue(inventoryDTO.getProductValue());
        inventory.setPurchaseDate(inventoryDTO.getPurchaseDate());
        inventory.setWarrantyPeriod(inventoryDTO.getWarrantyPeriod());
        inventory.setProductCategory(inventoryDTO.getProductCategory());
        inventory.setProductImage(inventoryDTO.getProductImage());

        Inventory savedProduct = inventoryRepository.save(inventory);
        inventoryDTO.setProductId(savedProduct.getProductId());
        return inventoryDTO;
    }

    @Override
    public InventoryDTO getProductById(String productId) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new InventoryDTO(inventory.getProductId(), inventory.getUserId(), inventory.getProductName(),
        inventory.getQuantity(), inventory.getProductValue(), inventory.getPurchaseDate(),
        inventory.getWarrantyPeriod(), inventory.getProductCategory(), inventory.getProductImage());
    }

    @Override
    public List<InventoryDTO> getAllProducts() {
        return inventoryRepository.findAll().stream().map(inventory -> 
            new InventoryDTO(inventory.getProductId(), inventory.getUserId(), inventory.getProductName(),
            inventory.getQuantity(), inventory.getProductValue(), inventory.getPurchaseDate(),
            inventory.getWarrantyPeriod(), inventory.getProductCategory(), inventory.getProductImage())
        ).collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getProductsByUserId(String userId) {
        return inventoryRepository.findByUserId(userId).stream().map(inventory ->
            new InventoryDTO(inventory.getProductId(), inventory.getUserId(), inventory.getProductName(),
            inventory.getQuantity(), inventory.getProductValue(), inventory.getPurchaseDate(),
            inventory.getWarrantyPeriod(), inventory.getProductCategory(), inventory.getProductImage())
        ).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO updateProduct(String productId, InventoryDTO inventoryDTO) {
        Inventory existingProduct = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductName(inventoryDTO.getProductName());
        existingProduct.setQuantity(inventoryDTO.getQuantity());
        existingProduct.setProductValue(inventoryDTO.getProductValue());
        existingProduct.setPurchaseDate(inventoryDTO.getPurchaseDate());
        existingProduct.setWarrantyPeriod(inventoryDTO.getWarrantyPeriod());
        existingProduct.setProductCategory(inventoryDTO.getProductCategory());
        existingProduct.setProductImage(inventoryDTO.getProductImage());

        inventoryRepository.save(existingProduct);
        return inventoryDTO;
    }

    @Override
    public void deleteProduct(String productId) {
        inventoryRepository.deleteById(productId);
    }

    
}
