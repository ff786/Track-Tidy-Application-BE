package lk.sliit.itpm.demo.controller;

import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.service.TrackTidyInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("inventory")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InventoryTrackController {

    private final TrackTidyInventoryService trackTidyInventoryService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public InventoryTrackController(TrackTidyInventoryService trackTidyInventoryService) {
        this.trackTidyInventoryService = trackTidyInventoryService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackInventory> createTidyInventory(
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("dob") @NotNull String date,
            @RequestParam("gender") @NotNull String gender,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("email") @NotNull String email,
            @RequestParam("serviceType") @NotNull String serviceType,
            @RequestParam("receipt") @NotNull MultipartFile receipt) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .memberId(memberId)
                .dob(dateFormat.parse(date))
                .gender(gender)
                .phoneNumber(phoneNumber)
                .email(email)
                .serviceType(serviceType)
                .receipt(receipt.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.createTidyInventory(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyInventory(@RequestParam("id") @NotNull String id) {
        trackTidyInventoryService.deleteTidyInventory(id);
    }

    @GetMapping("getAll")
    public List<TrackInventory> getAllTidyInventory() {
        return trackTidyInventoryService.getAllTidyInventory();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TrackInventory> updateTidyInventory(
            @PathVariable("id") @NotNull String id,
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("email") @NotNull String email,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("amount") @NotNull int amount,
            @RequestParam("receipt") MultipartFile receipt) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .memberId(memberId)
                .email(email)
                .phoneNumber(phoneNumber)
                .amount(amount)
                .receipt(receipt.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.updateTidyInventory(id, build));

    }
}
