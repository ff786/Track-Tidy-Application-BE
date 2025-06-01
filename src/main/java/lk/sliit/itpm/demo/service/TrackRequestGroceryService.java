package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackGroceryRequest;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;

import java.util.List;

public interface TrackRequestGroceryService {

    TrackGroceryRequest createGroceryRequest(TidyGroceryDTO groceryRequest);
    List<TidyGroceryDTO> getAllTidyGroceryRequest();
    TrackGroceryRequest getGroceryRequestById(String TrackId);
    void deleteGroceryRequest(String TrackId);
    void approveTidyGrocery(String id);

}
