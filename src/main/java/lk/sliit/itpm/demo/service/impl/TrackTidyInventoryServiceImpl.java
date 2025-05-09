package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;
import lk.sliit.itpm.demo.repository.TrackInventoryRepository;
import lk.sliit.itpm.demo.service.TrackTidyInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackTidyInventoryServiceImpl implements TrackTidyInventoryService {

    private final ModelMapper modelMapper;
    private final TrackInventoryRepository trackInventoryRepository;

    public TrackTidyInventoryServiceImpl(ModelMapper modelMapper, TrackInventoryRepository trackInventoryRepository) {
        this.modelMapper = modelMapper;
        this.trackInventoryRepository = trackInventoryRepository;
    }

    @Override
    public TrackInventory createTidyInventory(TidyInventoryDTO inventory) {
        TrackInventory map =TrackInventory.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .productName(inventory.getProductName())
                .productCategory(inventory.getProductCategory())
                .quantity(inventory.getQuantity())
                .WarrantyPeriod(inventory.getWarrantyPeriod())
                .productValue(inventory.getProductValue())
                .ProductImage(inventory.getProductImage())
                .build();

        return trackInventoryRepository.save(map);
    }

    @Override
    public void deleteTidyInventory(String id) {
        Optional<TrackInventory> byId = trackInventoryRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + id);
        }
        trackInventoryRepository.deleteById(id);
    }

    @Override
    public List<TrackInventoryResponseDTO> getAllTidyInventory() {
        List<TrackInventory> inventoryList = trackInventoryRepository.findAll();
        return inventoryList.stream().map(item -> {
            String base64Image = item.getProductImage() != null
                    ? "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(item.getProductImage())
                    : null;

            return TrackInventoryResponseDTO.builder()
                    .id(item.getId())
                    .productId(item.getProductId())
                    .productName(item.getProductName())
                    .productCategory(item.getProductCategory())
                    .productValue(item.getProductValue())
                    .WarrantyPeriod(item.getWarrantyPeriod())
                    .quantity(item.getQuantity())
                    .productImageBase64(base64Image)
                    .build();
        }).toList();
    }

    @Override
    public TrackInventory getInventoryById(String id) {

        Optional<TrackInventory> inventory = trackInventoryRepository.findById(id);
        if (!inventory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + id);
        }
        return inventory.get();
    }


    @Override
    public TrackInventory updateTidyInventory(String id, TidyInventoryDTO inventory) {

        Optional<TrackInventory> byId = trackInventoryRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + id);
        }
        TrackInventory trackInventory1 = byId.get();
        trackInventory1.setProductName(inventory.getProductName());
        trackInventory1.setProductId(inventory.getProductId());
        trackInventory1.setProductCategory(inventory.getProductCategory());
        trackInventory1.setQuantity(inventory.getQuantity());
        trackInventory1.setWarrantyPeriod(inventory.getWarrantyPeriod());
        trackInventory1.setProductValue(inventory.getProductValue());
        if (inventory.getProductImage() != null) {
            trackInventory1.setProductImage(inventory.getProductImage());
        }

        return trackInventoryRepository.save(trackInventory1);
    }

    /*@Override
    public void approveTidyInventory(String id) {

    }*/
}
