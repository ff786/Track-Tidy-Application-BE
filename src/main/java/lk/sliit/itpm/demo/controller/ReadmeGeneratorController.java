package lk.sliit.itpm.demo.controller;

import lk.sliit.itpm.demo.dto.UserPromptRequest;
import lk.sliit.itpm.demo.service.PackageRecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track-ai")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReadmeGeneratorController {

    private final PackageRecommendationService packageRecommendationService;


    public ReadmeGeneratorController(PackageRecommendationService packageRecommendationService) {
        this.packageRecommendationService = packageRecommendationService;
    }

    @PostMapping("/generate-package")
    public ResponseEntity<String> recommendPackage(@RequestBody UserPromptRequest request) {
        String result = packageRecommendationService.generatePackage(request.getUserPromptRequest());
        System.out.println("This is request" + request.toString());
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate package.");
        }
    }

}

