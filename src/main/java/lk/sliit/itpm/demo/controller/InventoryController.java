package lk.sliit.itpm.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.sliit.itpm.demo.document.inventory;
import lk.sliit.itpm.demo.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inven")
public class InventoryController {
     private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<inventory> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<inventory> getItemById(@PathVariable String id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public inventory createItem(@RequestBody inventory item) {
        return service.createItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<inventory> updateItem(@PathVariable String id, @RequestBody inventory item) {
        inventory updated = service.updateItem(id, item);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
    
}
