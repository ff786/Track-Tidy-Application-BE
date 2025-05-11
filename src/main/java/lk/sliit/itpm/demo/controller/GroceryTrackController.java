package lk.sliit.itpm.demo.controller;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackGrocery;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.TidyGroceryDTO;
import lk.sliit.itpm.demo.service.TrackTidyGroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @RequestParam("itemName") @NotNull String itemName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("price") @NotNull int price,
            @RequestParam("expiryDate") @NotNull String expiryDate,
            @RequestParam("itemImage") @NotNull MultipartFile itemImage) throws ParseException, IOException {

        TidyGroceryDTO build = TidyGroceryDTO.builder()
                .itemName(itemName)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .expiryDate(dateFormat.parse(expiryDate))
                .itemImage(itemImage.getBytes())
                .build();

        return ResponseEntity.status(201).body(trackTidyGroceryService.createTidyGrocery(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyInventory(@RequestParam("id") @NotNull String id) {
        trackTidyGroceryService.deleteTidyGrocery(id);
    }

    @GetMapping("getAll")
    public List<TidyGroceryDTO> getAllTidyGrocery() {
        return trackTidyGroceryService.getAllTidyGrocery();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("update/{id}")
    public ResponseEntity<TrackGrocery> updateTidyGrocery(
            @AuthenticationPrincipal User user,
            @PathVariable("id") @NotNull String id,
            @RequestParam("itemName") @NotNull String itemName,
            @RequestParam("productId") @NotNull String productId,
            @RequestParam("expiryDate") @NotNull String expiryDate,
            @RequestParam("quantity") @NotNull int quantity,
            @RequestParam("price") @NotNull int price,
            @RequestParam(value = "itemImage", required = false) @Nullable MultipartFile itemImage) throws ParseException, IOException {

        TrackGrocery grocery = trackTidyGroceryService.getGroceryById(id);

        if (itemName != null){
            grocery.setItemName(itemName);
        }
        grocery.setUserId(user.getEmail());
        grocery.setProductId(productId);
        grocery.setExpiryDate(dateFormat.parse(expiryDate));
        grocery.setQuantity(quantity);
        grocery.setPrice(price);

        if (itemImage != null){
            grocery.setItemImage(itemImage.getBytes());
        }

        TidyGroceryDTO build = TidyGroceryDTO.builder()
                .itemName(grocery.getItemName())
                .userId(grocery.getUserId())
                .productId(grocery.getProductId())
                .quantity(grocery.getQuantity())
                .price(grocery.getPrice())
                .expiryDate(grocery.getExpiryDate())
                .itemImage(grocery.getItemImage())
                .build();

        return ResponseEntity.status(201).body(trackTidyGroceryService.updateTidyGrocery(id, build));

    }
}
