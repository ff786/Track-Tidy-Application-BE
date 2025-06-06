package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.document.TrackInventoryRequest;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;
import lk.sliit.itpm.demo.repository.TrackInventoryRepository;
import lk.sliit.itpm.demo.repository.TrackInventoryRequestRepository;
import lk.sliit.itpm.demo.service.TrackRequestInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackRequestInventoryServiceImpl implements TrackRequestInventoryService {

    private final TrackInventoryRequestRepository trackInventoryRequestRepository;

    public TrackRequestInventoryServiceImpl(TrackInventoryRequestRepository trackInventoryRequestRepository) {
        this.trackInventoryRequestRepository = trackInventoryRequestRepository;
    }


    @Override
    public TrackInventoryRequest createTidyInventory(TidyInventoryDTO inventory) {
        TrackInventoryRequest map = TrackInventoryRequest.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .productName(inventory.getProductName())
                .quantity(inventory.getQuantity())
                .productCategory(inventory.getProductCategory())
                .requestDate(inventory.getRequestDate())
                .build();

        return trackInventoryRequestRepository.save(map);
    }

    @Override
    public void deleteInventoryRequest(String TrackId) {
        Optional<TrackInventoryRequest> byId = trackInventoryRequestRepository.findById(TrackId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackId);
        }
        trackInventoryRequestRepository.deleteById(TrackId);
    }

    @Override
    public List<TrackInventoryResponseDTO> getAllTidyRequestInventory() {
        List<TrackInventoryRequest> inventoryReqList = trackInventoryRequestRepository.findAll();
        return inventoryReqList.stream().map(item -> {
            String base64Image = item.getProductImage() != null
                    ? "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(item.getProductImage())
                    : null;
            return TrackInventoryResponseDTO.builder()
                    .id(item.getId())
                    .productId(item.getProductId())
                    .productName(item.getProductName())
                    .quantity(item.getQuantity())
                    .requestDate(item.getRequestDate())
                    .productCategory(item.getProductCategory())

                    .productImageBase64(base64Image)
                    .build();
        }).toList();
    }

    @Override
    public void approveTidyInventory(String id) {
        Optional<TrackInventoryRequest> byId = trackInventoryRequestRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + id);
        }

        TrackInventoryRequest track1 = byId.get();
        track1.setApproved(true);

        trackInventoryRequestRepository.save(track1);

    }
}
