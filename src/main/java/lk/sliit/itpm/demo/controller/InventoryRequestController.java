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
import java.util.Arrays;

@RestController
@RequestMapping("inventory/request")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InventoryRequestController {

    private final TrackTidyInventoryService trackTidyInventoryService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public InventoryRequestController(TrackTidyInventoryService trackTidyInventoryService) {
        this.trackTidyInventoryService = trackTidyInventoryService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackInventory> createTidyInventory(
            @RequestParam("productName") @NotNull String productName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("WarrantyPeriod") @NotNull int WarrantyPeriod,
            @RequestParam("productValue") @NotNull int productValue,
            @RequestParam("productCategory") @NotNull String productCategory,
            @RequestParam("ProductImage") @NotNull MultipartFile ProductImage) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .productName(productName)
                .productId(productId)
                .quantity(quantity)
                .WarrantyPeriod(WarrantyPeriod)
                .productValue(productValue)
                .productCategory(productCategory)
                .productImageBase64(Arrays.toString(ProductImage.getBytes()))
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.createTidyInventory(build));

    }

}
