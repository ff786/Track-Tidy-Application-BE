package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.SMSOTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMSRepository extends MongoRepository<SMSOTP, String> {

    SMSOTP findByEmail(String email);
}
