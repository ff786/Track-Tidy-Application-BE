package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;

import java.util.List;

public interface TrackTidyGroceryService {

    TrackGrocery createTidyGrocery(TidyGroceryDTO grocery);
    void deleteTidyGrocery(String TrackTidyId);
    List<TidyGroceryDTO> getAllTidyGrocery();
    TrackGrocery getGroceryById(String TrackTidyId);
    TrackGrocery updateTidyGrocery(String TrackTidyId, TidyGroceryDTO grocery);
//    void approveTidyGrocery(String id);
}
