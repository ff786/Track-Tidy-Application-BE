package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.repository.TrackGroceryRepository;
import lk.sliit.itpm.demo.service.TrackTidyGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackTidyGroceryServiceImpl implements TrackTidyGroceryService {

    private final ModelMapper modelMapper;
    private final TrackGroceryRepository trackGroceryRepository;

    public TrackTidyGroceryServiceImpl(ModelMapper modelMapper, TrackGroceryRepository trackGroceryRepository) {
        this.modelMapper = modelMapper;
        this.trackGroceryRepository = trackGroceryRepository;
    }

    @Override
    public TrackGrocery createTidyGrocery(TidyGroceryDTO grocery) {
        TrackGrocery map =TrackGrocery.builder()
                .id(grocery.getId())
                .itemName(grocery.getItemName())
                .productId(grocery.getProductId())
                .quantity(grocery.getQuantity())
                .price(grocery.getPrice())
                .expiryDate(grocery.getExpiryDate())
                .itemImage(grocery.getItemImage())
                .build();

        return trackGroceryRepository.save(map);
    }

    @Override
    public void deleteTidyGrocery(String TrackTidyId) {
        Optional<TrackGrocery> byId = trackGroceryRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        trackGroceryRepository.deleteById(TrackTidyId);
    }

    @Override
    public List<TidyGroceryDTO> getAllTidyGrocery() {
        List<TrackGrocery> groceryList = trackGroceryRepository.findAll();
        return groceryList.stream().map(item -> {
            String base64Image = item.getItemImage() != null
                    ? "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(item.getItemImage())
                    : null;

            return TidyGroceryDTO.builder()
                    .id(item.getId())
                    .itemName(item.getItemName())
                    .productId(item.getProductId())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .expiryDate(item.getExpiryDate())
                    .productImageBase64(base64Image)
                    .build();
        }).toList();
    }

    @Override
    public TrackGrocery getGroceryById(String TrackTidyId) {

        Optional<TrackGrocery> grocery = trackGroceryRepository.findById(TrackTidyId);
        if (!grocery.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        return grocery.get();
    }

    @Override
    public TrackGrocery updateTidyGrocery(String TrackTidyId, TidyGroceryDTO grocery) {

        Optional<TrackGrocery> byId = trackGroceryRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        TrackGrocery trackGrocery1 = byId.get();
        trackGrocery1.setItemName(grocery.getItemName());
        trackGrocery1.setProductId(grocery.getProductId());
        trackGrocery1.setQuantity(grocery.getQuantity());
        trackGrocery1.setPrice(grocery.getPrice());
        trackGrocery1.setExpiryDate(grocery.getExpiryDate());
        if (grocery.getItemImage() != null) {
            trackGrocery1.setItemImage(grocery.getItemImage());
        }

        return trackGroceryRepository.save(trackGrocery1);
    }

    /*@Override
    public void approveTidyGrocery(String id) {

    }*/
}
