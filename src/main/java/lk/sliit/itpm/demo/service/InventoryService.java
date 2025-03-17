package lk.sliit.itpm.demo.service;

import java.util.List;
import lk.sliit.itpm.demo.dto.InventoryDTO;

public interface InventoryService {

    InventoryDTO createProduct(InventoryDTO InventoryDTO);
    InventoryDTO getProductById(String productId);
    List<InventoryDTO> getAllProducts();
    List<InventoryDTO> getProductsByUserId(String userId);
    InventoryDTO updateProduct(String productId, InventoryDTO inventoryDTO);
    void deleteProduct(String productId);
    
}
