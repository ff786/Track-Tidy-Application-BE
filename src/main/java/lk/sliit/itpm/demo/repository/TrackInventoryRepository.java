package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.TrackInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackInventoryRepository extends MongoRepository<TrackInventory, String> {
}
