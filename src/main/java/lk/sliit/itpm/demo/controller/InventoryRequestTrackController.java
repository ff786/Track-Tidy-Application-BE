package lk.sliit.itpm.demo.controller;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackInventoryRequest;
import lk.sliit.itpm.demo.dto.TidyInventoryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;
import lk.sliit.itpm.demo.service.TrackRequestInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("inventory/request")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InventoryRequestTrackController {

    private final TrackRequestInventoryService trackRequestInventoryService;

    public InventoryRequestTrackController(TrackRequestInventoryService trackRequestInventoryService) {
        this.trackRequestInventoryService = trackRequestInventoryService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackInventoryRequest> createTidyInventory(
            @RequestParam("productName") @NotNull String productName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("productValue") @NotNull int productValue,
            @RequestParam("productCategory") @NotNull String productCategory) throws ParseException, IOException {

        TidyInventoryDTO build = TidyInventoryDTO.builder()
                .productName(productName)
                .productId(productId)
                .quantity(quantity)
                .productValue(productValue)
                .productCategory(productCategory)
                .build();
        return ResponseEntity.status(201).body(trackRequestInventoryService.createTidyInventory(build));
    }

    @GetMapping("getAll")
    public List<TrackInventoryResponseDTO> getAllTidyRequestInventory() {
        return trackRequestInventoryService.getAllTidyRequestInventory();
    }

    @DeleteMapping("delete")
    public void deleteInventoryRequest(@RequestParam("id") @NotNull String id) {
        trackRequestInventoryService.deleteInventoryRequest(id);
    }

    @PutMapping("approve/{id}")
    public void approveTidyInventory(@PathVariable("id") @NotNull String id) {
        trackRequestInventoryService.approveTidyInventory(id);
    }
}
