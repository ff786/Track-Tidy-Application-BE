package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.dto.TidyServiceDTO;
import lk.sliit.itpm.demo.repository.TrackServiceRepository;
import lk.sliit.itpm.demo.service.TrackTidyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackTidyServiceImpl implements TrackTidyService {

    private final ModelMapper modelMapper;
    private final TrackServiceRepository trackServiceRepository;

    public TrackTidyServiceImpl(ModelMapper modelMapper, TrackServiceRepository trackServiceRepository) {
        this.modelMapper = modelMapper;
        this.trackServiceRepository = trackServiceRepository;
    }


    @Override
    public TrackService createTidyService(TidyServiceDTO service) {

        TrackService map =TrackService.builder()
                .userId(service.getUserId())
                .memberName(service.getMemberName())
                .address(service.getAddress())
                .email(service.getEmail())
                .phoneNumber(service.getPhoneNumber())
                .serviceType(service.getServiceType())
                .serviceDesc(service.getServiceDesc())
                .referralCode(service.getReferralCode()).build();

        return trackServiceRepository.save(map);
    }

    @Override
    public void deleteTidyService(String TrackTidyId) {
        Optional<TrackService> byId = trackServiceRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        trackServiceRepository.deleteById(TrackTidyId);
    }

    @Override
    public List<TrackService> getAllTidyService() {
        return trackServiceRepository.findAll();
    }

    @Override
    public TrackService getTidServiceById(String TrackTidyId) {

        Optional<TrackService> service = trackServiceRepository.findById(TrackTidyId);
        if (!service.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        return service.get();
    }

    @Override
    public TrackService updateTidyService(String TrackTidyId, TidyServiceDTO service) {

        Optional<TrackService> byId = trackServiceRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        TrackService trackService1 = byId.get();
        trackService1.setMemberName(service.getMemberName());
        trackService1.setEmail(service.getEmail());
        trackService1.setServiceType(service.getServiceType());
        trackService1.setServiceDesc(service.getServiceDesc());
        trackService1.setPhoneNumber(service.getPhoneNumber());
        trackService1.setAddress(service.getAddress());

        return trackServiceRepository.save(trackService1);
    }

    /*@Override
    public void approveTidyService(String id) {
    }*/
}
