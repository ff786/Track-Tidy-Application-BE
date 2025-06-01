package lk.sliit.itpm.demo.controller;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackPackage;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.TidyPackageDTO;
import lk.sliit.itpm.demo.service.TrackTidyPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("package")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PackageController {

    private final TrackTidyPackageService trackTidyPackageService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PackageController(TrackTidyPackageService trackTidyPackageService) {
        this.trackTidyPackageService = trackTidyPackageService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackPackage> createTidyPackage(
            @AuthenticationPrincipal User user,
            @RequestParam("packageType") @NotNull String packageType,
            @RequestParam("packageValue") @NotNull int packageValue,
            @RequestParam("groceryValue") @NotNull int groceryValue,
            @RequestParam("serviceValue") @NotNull int serviceValue,
            @RequestParam("inventoryValue") @NotNull int inventoryValue,
            @RequestParam("subscribedDate") @Nullable String subscribedDate ) throws ParseException, IOException {

        TidyPackageDTO build = TidyPackageDTO.builder()
                .userId(user.getEmail())
                .packageType(packageType)
                .packageValue(packageValue)
                .groceryValue(groceryValue)
                .serviceValue(serviceValue)
                .inventoryValue(inventoryValue)
                .subscribeDate(dateFormat.parse(subscribedDate))
                .build();

        return ResponseEntity.status(201).body(trackTidyPackageService.createTidyPackage(build));

    }

    @DeleteMapping("delete")
    public void deleteTidyPackages(@RequestParam("id") @NotNull String id) {
        trackTidyPackageService.deleteTidyPackages(id);
    }

    @GetMapping("getAll")
    public List<TrackPackage> getAllTidyPackages() {
        return trackTidyPackageService.getAllTidyPackages();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TrackPackage> updateTidyPackages(
            @PathVariable("id") @NotNull String id,
            @RequestParam("packageType") @NotNull String packageType,
            @RequestParam("packageValue") @NotNull int packageValue,
            @RequestParam("groceryValue") @NotNull int groceryValue,
            @RequestParam("serviceValue") @NotNull int serviceValue,
            @RequestParam("inventoryValue") @NotNull int inventoryValue ) throws ParseException, IOException {

        TidyPackageDTO build = TidyPackageDTO.builder()
                .packageType(packageType)
                .packageValue(packageValue)
                .groceryValue(groceryValue)
                .serviceValue(serviceValue)
                .inventoryValue(inventoryValue)
                .build();

        return ResponseEntity.status(201).body(trackTidyPackageService.updateTidyPackages(id, build));

    }

    @PutMapping("extend/{id}")
    public void extendTidyPackages(@PathVariable("id") @NotNull String id) {
        trackTidyPackageService.extendTidyPackages(id);
    }
}