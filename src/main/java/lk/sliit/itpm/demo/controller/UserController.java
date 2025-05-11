package lk.sliit.itpm.demo.controller;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.GetUserDTO;
import lk.sliit.itpm.demo.dto.TokenResponse;
import lk.sliit.itpm.demo.dto.UserDTO;
import lk.sliit.itpm.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody @Validated UserDTO user) {
        log.info("User Registration for [{}]", user);
        return userService.registerUser(user);
    }

    @PostMapping("request/token")
    public TokenResponse getAccessToken(@RequestBody JsonNode jsonNode) {
        return userService.generateToken(jsonNode.get("email").asText(), jsonNode.get("password").asText());
    }

    @GetMapping("getUsers")
    public List<GetUserDTO> getUsers(@NotNull @RequestParam String userType) {
        return userService.getUsers(userType);
    }

    @PostMapping("otp/request")
    public void requestOTP(@RequestBody JsonNode jsonNode) {
        userService.requestOTP(jsonNode.get("email").asText(), jsonNode.get("password").asText());
    }

}
