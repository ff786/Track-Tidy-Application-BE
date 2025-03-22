package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.TrackGrocery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackGroceryRepository extends MongoRepository<TrackGrocery, String> {
}
