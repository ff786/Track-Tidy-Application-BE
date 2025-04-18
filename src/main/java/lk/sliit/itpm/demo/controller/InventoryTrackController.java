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
            @RequestParam("productName") @NotNull String productName,
            @RequestParam("userId") @NotNull String userId,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("purchaseDate") @NotNull String purchaseDate,
            @RequestParam("productValue") @NotNull int productValue,
            @RequestParam("warrantyDate") @NotNull String warrantyDate,
            @RequestParam("productCategory") @NotNull String productCategory,
            @RequestParam("Faulted") @NotNull String Faulted,
            @RequestParam("ProductImage") @NotNull MultipartFile ProductImage) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .productName(productName)
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .purchaseDate(dateFormat.parse(purchaseDate))
                .productValue(productValue)
                .warrantyDate(dateFormat.parse(warrantyDate))
                .faulted(Faulted)
                .productCategory(productCategory)
                .ProductImage(ProductImage.getBytes())
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
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("productValue") @NotNull int productValue,
            @RequestParam("productCategory") @NotNull String productCategory,
            @RequestParam("faulted") @NotNull String faulted,
            @RequestParam("ProductImage") @NotNull MultipartFile ProductImage) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .productId(productId)
                .quantity(quantity)
                .productValue(productValue)
                .productCategory(productCategory)
                .faulted(faulted)
                .ProductImage(ProductImage.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.updateTidyInventory(id, build));

    }
}
