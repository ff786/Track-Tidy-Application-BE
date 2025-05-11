package lk.sliit.itpm.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.TokenResponse;
import lk.sliit.itpm.demo.dto.UserDTO;
import lk.sliit.itpm.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> registerAdmin(@RequestBody UserDTO user) {
        return userService.registerAdmin(user);
    }

    @PostMapping("otp/request")
    public void requestOTP(@RequestBody JsonNode jsonNode) {
        userService.requestOTP(jsonNode.get("email").asText(), jsonNode.get("password").asText());
    }

    @PostMapping("request/token")
    public TokenResponse getAccessToken(@RequestBody JsonNode jsonNode) {
        return userService.generateToken(jsonNode.get("email").asText(), jsonNode.get("otp").asText());
    }

    @GetMapping("getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("delete/{id}")
    public void getAllUsers(@PathVariable String id) {
        userService.deleteUSer(id);
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserDTO user) {
        return userService.updateUser(id, user);
    }


}
