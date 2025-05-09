package lk.sliit.itpm.demo.service.impl;

import lk.sliit.itpm.demo.document.TrackPackage;
import lk.sliit.itpm.demo.dto.TidyPackageDTO;
import lk.sliit.itpm.demo.repository.TrackPackagesRepository;
import lk.sliit.itpm.demo.service.TrackTidyPackageService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackTidyPackageServiceImpl implements TrackTidyPackageService {

    private final ModelMapper modelMapper;
    private final TrackPackagesRepository trackPackagesRepository;

    public TrackTidyPackageServiceImpl(ModelMapper modelMapper, TrackPackagesRepository trackPackagesRepository) {
        this.modelMapper = modelMapper;
        this.trackPackagesRepository = trackPackagesRepository;
    }

    @Override
    public TrackPackage createTidyPackage(TidyPackageDTO packages) {

        TrackPackage map = TrackPackage.builder()
                .id(packages.getId())
                .userId(packages.getUserId())
                .packageType(packages.getPackageType())
                .packageValue(packages.getPackageValue())
                .groceryValue(packages.getGroceryValue())
                .inventoryValue(packages.getInventoryValue())
                .serviceValue(packages.getServiceValue())
                .subscribeDate(packages.getSubscribeDate())
                .build();

        return trackPackagesRepository.save(map);
    }

    @Override
    public void deleteTidyPackages(String TrackTidyId) {
        Optional<TrackPackage> byId = trackPackagesRepository.findById(TrackTidyId);
        if (!byId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find Service with TrackTidyId: " + TrackTidyId);
        }
        trackPackagesRepository.deleteById(TrackTidyId);
    }

    @Override
    public List<TrackPackage> getAllTidyPackages() {
        return trackPackagesRepository.findAll();
    }

    @Override
    public TrackPackage updateTidyPackages(String TrackTidyId, TidyPackageDTO packages) {
        return null;
    }
}
