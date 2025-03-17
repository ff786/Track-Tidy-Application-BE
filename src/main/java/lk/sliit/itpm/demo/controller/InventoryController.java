package lk.sliit.itpm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lk.sliit.itpm.demo.dto.InventoryDTO;
import lk.sliit.itpm.demo.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public InventoryDTO createProduct(@Valid @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.createProduct(inventoryDTO);
    }

    @GetMapping("/{productId}")
    public InventoryDTO getProductById(@PathVariable String productId) {
        return inventoryService.getProductById(productId);
    }

    @GetMapping("/user/{userId}")
    public List<InventoryDTO> getProductsByUserId(@PathVariable String userId) {
        return inventoryService.getProductsByUserId(userId);
    }

    @PutMapping("/{productId}")
    public InventoryDTO updateProduct(@PathVariable String productId, @Valid @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateProduct(productId, inventoryDTO);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        inventoryService.deleteProduct(productId);
    }
    
}
