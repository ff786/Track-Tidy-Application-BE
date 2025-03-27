package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.repository.TrackInventoryRepository;
import lk.sliit.itpm.demo.service.TrackTidyInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackTidyInventoryServiceImpl implements TrackTidyInventoryService {

    private final TrackInventoryRepository trackInventoryRepository;

    public TrackTidyInventoryServiceImpl(TrackInventoryRepository trackInventoryRepository) {
        this.trackInventoryRepository = trackInventoryRepository;
    }

    @Override
    public TrackInventory createTidyInventory(TidyInventoryDTO inventory) {
        TrackInventory newInventory = TrackInventory.builder()
        .userId(inventory.getUserId())
        .productName(inventory.getProductName())
        .productId(inventory.getProductId())
        .quantity(inventory.getQuantity())
        .purchaseDate(inventory.getPurchaseDate())
        .productValue(inventory.getProductValue())
        .warrantyDate(inventory.getWarrantyDate())
        .productCategory(inventory.getProductCategory())
        .ProductImage(inventory.getProductImage())
        .Faulted(inventory.getFaulted())
        .approvedBy(null) // Initially not approved
        .build();
    return trackInventoryRepository.save(newInventory);
    }

    @Override
    public void deleteTidyInventory(String TrackTidyId) {
        trackInventoryRepository.deleteById(TrackTidyId);
    }

    @Override
    public List<TrackInventory> getAllTidyInventory() {
        return trackInventoryRepository.findAll();
    }

    @Override
    public Optional<TrackInventory> getTidyInventoryById(String id) {
        return trackInventoryRepository.findById(id);
    }

    @Override
    public TrackInventory updateTidyInventory(String TrackTidyId, TidyInventoryDTO inventory) {
        return trackInventoryRepository.findById(TrackTidyId)
                .map(existing -> {
                    existing.setProductId(inventory.getProductId());
                    existing.setQuantity(inventory.getQuantity());
                    existing.setProductValue(inventory.getProductValue());
                    existing.setProductCategory(inventory.getProductCategory());
                    existing.setFaulted(inventory.getFaulted());
                    if (inventory.getProductImage() != null) {
                        existing.setProductImage(inventory.getProductImage());
                    }
                    return trackInventoryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + TrackTidyId));
    }

    @Override
    public void approveTidyInventory(String id) {

    }
}
