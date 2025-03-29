package lk.sliit.itpm.demo.service;

import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.GetUserDTO;
import lk.sliit.itpm.demo.dto.TokenResponse;
import lk.sliit.itpm.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {

    ResponseEntity<User> registerUser(UserDTO user);
    //ResponseEntity<User> registerAdmin(UserDTO admin);
    UserDetailsService userDetailsService();
    TokenResponse generateToken(String email, String password);
    List<GetUserDTO> getUsers(String userType);
    List<User> getAllUsers();
    void deleteUSer(String id);
    User updateUser(String id, UserDTO user);
    void requestOTP(String email, String password);
}
