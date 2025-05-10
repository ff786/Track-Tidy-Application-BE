package lk.sliit.itpm.demo.controller;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackInventory;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;
import lk.sliit.itpm.demo.service.TrackTidyInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("inventory")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InventoryTrackController {

    private final TrackTidyInventoryService trackTidyInventoryService;

    public InventoryTrackController(TrackTidyInventoryService trackTidyInventoryService) {
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
                .ProductImage(ProductImage.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.createTidyInventory(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyInventory(@RequestParam("id") @NotNull String id) {
        trackTidyInventoryService.deleteTidyInventory(id);
    }

    @GetMapping("getAll")
    public List<TrackInventoryResponseDTO> getAllTidyInventory() {
        return trackTidyInventoryService.getAllTidyInventory();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("update/{id}")
    public ResponseEntity<TrackInventory> updateTidyInventory(
            @AuthenticationPrincipal User user,
            @PathVariable("id") @NotNull String id,
            @RequestParam("productName") @NotNull String productName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("productValue") @NotNull int productValue,
            @RequestParam("warrantyPeriod") @NotNull int WarrantyPeriod,
            @RequestParam("productCategory") @NotNull String productCategory,
            @RequestParam(value = "ProductImage", required = false) @Nullable MultipartFile ProductImage) throws ParseException, IOException {

        TrackInventory inventory = trackTidyInventoryService.getInventoryById(id);

        if (productName != null){
            inventory.setProductName(productName);
        }
        inventory.setProductId(productId);
        inventory.setQuantity(quantity);
        inventory.setProductValue(productValue);
        inventory.setWarrantyPeriod(WarrantyPeriod);
        inventory.setProductCategory(productCategory);

        if (ProductImage != null){
            inventory.setProductImage(ProductImage.getBytes());
        }

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .productName(inventory.getProductName())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .productValue(inventory.getProductValue())
                .WarrantyPeriod(inventory.getWarrantyPeriod())
                .productCategory(inventory.getProductCategory())
                .ProductImage(inventory.getProductImage())
                .build();

        return ResponseEntity.status(201).body(trackTidyInventoryService.updateTidyInventory(id, build));

    }
}
