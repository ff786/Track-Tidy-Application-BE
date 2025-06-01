package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.TrackInventoryRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackInventoryRequestRepository extends MongoRepository<TrackInventoryRequest, String> {
}
