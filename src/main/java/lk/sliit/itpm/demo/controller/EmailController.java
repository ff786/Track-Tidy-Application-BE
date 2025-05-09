package lk.sliit.itpm.demo.controller;

import lk.sliit.itpm.demo.dto.EmailRequestDTO;
import lk.sliit.itpm.demo.util.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("email")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailController {

    private final EmailSender emailSender;

    public EmailController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequestDTO request) {
        try {
            emailSender.sendEmail(request.getTo(), request.getSubject(), request.getBody());
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Email sent successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to send email", e);
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
