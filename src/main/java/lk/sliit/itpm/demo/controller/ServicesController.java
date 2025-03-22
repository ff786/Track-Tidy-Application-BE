package lk.sliit.itpm.demo.controller;


import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.TrackTidy;
import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.dto.TidyServiceDTO;
import lk.sliit.itpm.demo.service.TrackTidyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("service")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicesController {

    private final TrackTidyService trackTidyService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ServicesController(TrackTidyService trackTidyService) {
        this.trackTidyService = trackTidyService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackService> createTidyService(
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("dob") @NotNull String date,
            @RequestParam("gender") @NotNull String gender,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("email") @NotNull String email,
            @RequestParam("serviceType") @NotNull String serviceType,
            @RequestParam("amount") @NotNull int amount,
            @RequestParam("diagnosisId") @NotNull String diagnosisId,
            @RequestParam("treatmentDate") @NotNull String treatmentDate,
            @RequestParam("receipt") @NotNull MultipartFile receipt) throws ParseException, IOException {

        TidyServiceDTO build = TidyServiceDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .memberId(memberId)
                .dob(dateFormat.parse(date))
                .gender(gender)
                .phoneNumber(phoneNumber)
                .email(email)
                .serviceType(serviceType)
                .amount(amount)
                .diagnosisId(diagnosisId)
                .treatmentDate(dateFormat.parse(treatmentDate))
                .receipt(receipt.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyService.createTidyService(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyService(@RequestParam("id") @NotNull String id) {
        trackTidyService.deleteTidyService(id);
    }

    @GetMapping("getAll")
    public List<TrackService> getAllTidyService() {
        return trackTidyService.getAllTidyService();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TrackService> updateTidyService(
            @PathVariable("id") @NotNull String id,
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("email") @NotNull String email,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("amount") @NotNull int amount,
            @RequestParam("treatmentDate") @NotNull String treatmentDate,
            @RequestParam("receipt") MultipartFile receipt) throws ParseException, IOException {

        TidyServiceDTO build = TidyServiceDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .memberId(memberId)
                .email(email)
                .phoneNumber(phoneNumber)
                .amount(amount)
                .treatmentDate(dateFormat.parse(treatmentDate))
                .receipt(receipt.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyService.updateTidyService(id, build));

    }
}
