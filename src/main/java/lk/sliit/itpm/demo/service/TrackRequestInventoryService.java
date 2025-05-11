package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;

import java.util.List;

public interface TrackRequestInventoryService {

    TrackInventory createTidyInventory(TidyInventoryDTO inventory);
    List<TrackInventoryResponseDTO> getAllTidyRequestInventory();
    void deleteInventoryRequest(String TrackId);
    void approveTidyInventory(String id);

}
