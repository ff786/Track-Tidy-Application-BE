package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.dto.TidyServiceDTO;
import lk.sliit.itpm.demo.repository.TrackServiceRepository;
import lk.sliit.itpm.demo.service.TrackTidyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrackTidyServiceImpl implements TrackTidyService {

    private final TrackServiceRepository trackServiceRepository;

    public TrackTidyServiceImpl(TrackServiceRepository trackServiceRepository) {
        this.trackServiceRepository = trackServiceRepository;
    }


    @Override
    public TrackService createTidyService(TidyServiceDTO service) {
        return null;
    }

    @Override
    public void deleteTidyService(String TrackTidyId) {

    }

    @Override
    public List<TrackService> getAllTidyService() {
        return trackServiceRepository.findAll();
    }

    @Override
    public TrackService updateTidyService(String TrackTidyId, TidyServiceDTO service) {
        return null;
    }

    @Override
    public void approveTidyService(String id) {

    }
}
