package lk.sliit.itpm.demo.repository;


import lk.sliit.itpm.demo.document.TrackService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackServiceRepository extends MongoRepository<TrackService, String>{
}
