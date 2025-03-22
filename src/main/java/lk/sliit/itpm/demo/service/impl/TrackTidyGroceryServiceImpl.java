package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.repository.TrackGroceryRepository;
import lk.sliit.itpm.demo.service.TrackTidyGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrackTidyGroceryServiceImpl implements TrackTidyGroceryService {

    private final TrackGroceryRepository trackGroceryRepository;

    public TrackTidyGroceryServiceImpl(TrackGroceryRepository trackGroceryRepository) {
        this.trackGroceryRepository = trackGroceryRepository;
    }

    @Override
    public TrackGrocery createTidyGrocery(TidyGroceryDTO grocery) {
        return null;
    }

    @Override
    public void deleteTidyGrocery(String TrackTidyId) {

    }

    @Override
    public List<TrackGrocery> getAllTidyGrocery() {
        return trackGroceryRepository.findAll();
    }

    @Override
    public TrackGrocery updateTidyGrocery(String TrackTidyId, TidyGroceryDTO grocery) {
        return null;
    }

    @Override
    public void approveTidyGrocery(String id) {

    }
}
