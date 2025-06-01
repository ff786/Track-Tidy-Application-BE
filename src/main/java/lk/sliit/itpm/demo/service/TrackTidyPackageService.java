package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.TrackPackage;
import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.dto.TidyPackageDTO;

import java.util.List;

public interface TrackTidyPackageService {

    TrackPackage createTidyPackage(TidyPackageDTO packages);
    void deleteTidyPackages(String TrackTidyId);
    List<TrackPackage> getAllTidyPackages();
    TrackPackage updateTidyPackages(String TrackTidyId, TidyPackageDTO packages);
    void extendTidyPackages(String id);

}
