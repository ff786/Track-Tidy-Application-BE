package lk.sliit.itpm.demo.repository;


import lk.sliit.itpm.demo.document.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface  InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByUserId(String userId); 
}
