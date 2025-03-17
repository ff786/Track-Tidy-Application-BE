package lk.sliit.itpm.demo.service;

import java.util.List;
import java.util.Optional;
import lk.sliit.itpm.demo.document.inventory;

public interface InventoryService {

    List<inventory> getAllItems();
    Optional<inventory> getItemById(String id);
    inventory createItem(inventory item);
    inventory updateItem(String id, inventory item);
    void deleteItem(String id);
    
}
