package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.repository.TrackInventoryRepository;
import lk.sliit.itpm.demo.service.TrackTidyInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrackTidyInventoryServiceImpl implements TrackTidyInventoryService {

    private final TrackInventoryRepository trackInventoryRepository;

    public TrackTidyInventoryServiceImpl(TrackInventoryRepository trackInventoryRepository) {
        this.trackInventoryRepository = trackInventoryRepository;
    }

    @Override
    public TrackInventory createTidyInventory(TidyInventoryDTO inventory) {
        return null;
    }

    @Override
    public void deleteTidyInventory(String TrackTidyId) {

    }

    @Override
    public List<TrackInventory> getAllTidyInventory() {
        return trackInventoryRepository.findAll();
    }

    @Override
    public TrackInventory updateTidyInventory(String TrackTidyId, TidyInventoryDTO inventory) {
        return null;
    }

    @Override
    public void approveTidyInventory(String id) {

    }
}
