package lk.sliit.itpm.demo.controller;

import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.service.TrackTidyGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("grocery")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroceryTrackController {

    private final TrackTidyGroceryService trackTidyGroceryService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GroceryTrackController(TrackTidyGroceryService trackTidyGroceryService) {
        this.trackTidyGroceryService = trackTidyGroceryService;
    }



    @PostMapping("create")
    public ResponseEntity<TrackGrocery> createTidyGrocery(
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("dob") @NotNull String date,
            @RequestParam("gender") @NotNull String gender,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("email") @NotNull String email,
            @RequestParam("serviceType") @NotNull String serviceType,
            @RequestParam("receipt") @NotNull MultipartFile receipt) throws ParseException, IOException {

        TidyGroceryDTO build = TidyGroceryDTO.builder()
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

        return ResponseEntity.status(201).body(trackTidyGroceryService.createTidyGrocery(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyInventory(@RequestParam("id") @NotNull String id) {
        trackTidyGroceryService.deleteTidyGrocery(id);
    }

    @GetMapping("getAll")
    public List<TrackGrocery> getAllTidyGrocery() {
        return trackTidyGroceryService.getAllTidyGrocery();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TrackGrocery> updateTidyGrocery(
            @PathVariable("id") @NotNull String id,
            @RequestParam("firstName") @NotNull String firstName,
            @RequestParam("lastName") @NotNull String lastName,
            @RequestParam("memberId") @NotNull String memberId,
            @RequestParam("email") @NotNull String email,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("amount") @NotNull int amount,
            @RequestParam("receipt") MultipartFile receipt) throws ParseException, IOException {

        TidyGroceryDTO build = TidyGroceryDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .memberId(memberId)
                .email(email)
                .phoneNumber(phoneNumber)
                .amount(amount)
                .receipt(receipt.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyGroceryService.updateTidyGrocery(id, build));

    }
}
