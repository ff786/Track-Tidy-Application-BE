package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.document.TrackService;
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
                .memberId(grocery.getMemberId())
                .itemName(grocery.getItemName())
                .phoneNumber(grocery.getPhoneNumber())
                .price(grocery.getPrice())
                .purchaseDate(grocery.getPurchaseDate())
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
    public List<TrackGrocery> getAllTidyGrocery() {
        return trackGroceryRepository.findAll();
    }

    @Override
    public TrackGrocery updateTidyGrocery(String TrackTidyId, TidyGroceryDTO grocery) {

        Optional<TrackGrocery> byId = trackGroceryRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        TrackGrocery trackGrocery1 = byId.get();
        trackGrocery1.setItemName(grocery.getItemName());
        trackGrocery1.setPhoneNumber(grocery.getPhoneNumber());
        trackGrocery1.setPrice(grocery.getPrice());
        trackGrocery1.setPurchaseDate(grocery.getPurchaseDate());
        trackGrocery1.setExpiryDate(grocery.getExpiryDate());
        trackGrocery1.setItemImage(grocery.getItemImage());

        return trackGroceryRepository.save(trackGrocery1);
    }

    /*@Override
    public void approveTidyGrocery(String id) {

    }*/
}
