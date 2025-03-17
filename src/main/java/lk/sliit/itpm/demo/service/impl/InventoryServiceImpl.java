package lk.sliit.itpm.demo.service.impl;

import java.util.List;
import java.util.Optional;
import lk.sliit.itpm.demo.document.inventory;
import lk.sliit.itpm.demo.repository.InventoryRepository;
import lk.sliit.itpm.demo.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<inventory> getAllItems() {
        return repository.findAll();
    }

    @Override
    public Optional<inventory> getItemById(String id) {
        return repository.findById(id);
    }

    @Override
    public inventory createItem(inventory item) {
        return repository.save(item);
    }

    @Override
    public inventory updateItem(String id, inventory item) {
        if (repository.existsById(id)) {
            item.setId(id);
            return repository.save(item);
        }
        return null;
    }

    @Override
    public void deleteItem(String id) {
        repository.deleteById(id);
    }
    
}
