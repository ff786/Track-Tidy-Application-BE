package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.dto.TidyServiceDTO;

import java.util.List;

public interface TrackTidyService {

    TrackService createTidyService(TidyServiceDTO service);
    void deleteTidyService(String TrackTidyId);
    List<TrackService> getAllTidyService();
    TrackService getTidServiceById(String TrackTidyId);
    TrackService updateTidyService(String TrackTidyId, TidyServiceDTO service);
    //void approveTidyService(String id);

}
