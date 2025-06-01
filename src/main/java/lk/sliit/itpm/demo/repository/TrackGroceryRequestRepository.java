package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.TrackGroceryRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackGroceryRequestRepository extends MongoRepository<TrackGroceryRequest, String> {
}
