package lk.sliit.itpm.demo.controller;


import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackGroceryRequest;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.dto.TrackInventoryResponseDTO;
import lk.sliit.itpm.demo.service.TrackRequestGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("grocery/request")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroceryRequestController {

    private final TrackRequestGroceryService trackRequestGroceryService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public GroceryRequestController(TrackRequestGroceryService trackRequestGroceryService) {
        this.trackRequestGroceryService = trackRequestGroceryService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackGroceryRequest> createTidyGrocery(
            @RequestParam("itemName") @NotNull String itemName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("price") @NotNull int price) throws ParseException, IOException {

        TidyGroceryDTO build = TidyGroceryDTO.builder()
                .itemName(itemName)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .build();

        return ResponseEntity.status(201).body(trackRequestGroceryService.createGroceryRequest(build));

    }

    @GetMapping("getAll")
    public List<TidyGroceryDTO> getAllTidyRequestGrocery() {
        return trackRequestGroceryService.getAllTidyGroceryRequest();
    }

    @DeleteMapping("delete")
    public void deleteInventoryRequest(@RequestParam("id") @NotNull String id) {
        trackRequestGroceryService.deleteGroceryRequest(id);
    }

    @PutMapping("approve/{id}")
    public void approveTidyInventory(@PathVariable("id") @NotNull String id) {
        trackRequestGroceryService.approveTidyGrocery(id);
    }

}
