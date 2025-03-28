package lk.sliit.itpm.demo.repository;

import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.util.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    
    Boolean existsUserByEmail(String email);
    List<User> findAllByRole(Role role);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);

}
