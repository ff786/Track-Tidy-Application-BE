package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.TrackPackage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackPackagesRepository extends MongoRepository<TrackPackage, String> {
}
