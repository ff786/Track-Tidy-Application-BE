package lk.sliit.itpm.demo.repository;


import lk.sliit.itpm.demo.document.inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface  InventoryRepository extends MongoRepository<inventory, String> {
    List<inventory> findByUserId(String userId); 
}
