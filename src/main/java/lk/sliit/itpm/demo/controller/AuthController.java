package lk.sliit.itpm.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lk.sliit.itpm.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody JsonNode jsonNode) {
        String email = jsonNode.get("email").asText();
        String newPassword = jsonNode.get("newPassword").asText();
        String confirmPassword = jsonNode.get("confirmPassword").asText();
        userService.resetPassword(email, newPassword);
        return ResponseEntity.ok().build();
    }
}
