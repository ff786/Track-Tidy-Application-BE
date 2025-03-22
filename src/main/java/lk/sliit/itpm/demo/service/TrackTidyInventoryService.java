package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;

import java.util.List;

public interface TrackTidyInventoryService {

    TrackInventory createTidyInventory(TidyInventoryDTO inventory);
    void deleteTidyInventory(String TrackTidyId);
    List<TrackInventory> getAllTidyInventory();
    TrackInventory updateTidyInventory(String TrackTidyId, TidyInventoryDTO inventory);
    void approveTidyInventory(String id);

}
