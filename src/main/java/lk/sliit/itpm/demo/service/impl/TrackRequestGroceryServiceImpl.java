package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackGroceryRequest;
import lk.sliit.itpm.demo.document.TrackInventoryRequest;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.repository.TrackGroceryRequestRepository;
import lk.sliit.itpm.demo.repository.TrackInventoryRequestRepository;
import lk.sliit.itpm.demo.service.TrackRequestGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackRequestGroceryServiceImpl implements TrackRequestGroceryService {

    private final TrackGroceryRequestRepository trackGroceryRequestRepository;
    private final ModelMapper modelMapper;

    public TrackRequestGroceryServiceImpl(TrackGroceryRequestRepository trackGroceryRequestRepository, ModelMapper modelMapper) {
        this.trackGroceryRequestRepository = trackGroceryRequestRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TrackGroceryRequest createGroceryRequest(TidyGroceryDTO grocery) {
        TrackGroceryRequest map =TrackGroceryRequest.builder()
                .id(grocery.getId())
                .itemName(grocery.getItemName())
                .productId(grocery.getProductId())
                .quantity(grocery.getQuantity())
                .price(grocery.getPrice())
                .build();

        return trackGroceryRequestRepository.save(map);
    }

    @Override
    public List<TidyGroceryDTO> getAllTidyGroceryRequest() {
        List<TrackGroceryRequest> groceryList = trackGroceryRequestRepository.findAll();
        return groceryList.stream().map(item -> {

            return TidyGroceryDTO.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .productId(item.getProductId())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .build();
        }).toList();
    }

    @Override
    public TrackGroceryRequest getGroceryRequestById(String TrackId) {
        return null;
    }

    @Override
    public void deleteGroceryRequest(String TrackId) {
        Optional<TrackGroceryRequest> byId = trackGroceryRequestRepository.findById(TrackId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackId);
        }
        trackGroceryRequestRepository.deleteById(TrackId);
    }

    @Override
    public void approveTidyGrocery(String id) {
        Optional<TrackGroceryRequest> byId = trackGroceryRequestRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + id);
        }

        TrackGroceryRequest track1 = byId.get();
        track1.setApproved(true);

        trackGroceryRequestRepository.save(track1);
    }
}
