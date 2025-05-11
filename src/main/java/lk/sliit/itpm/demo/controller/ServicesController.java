package lk.sliit.itpm.demo.controller;

import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.document.TrackService;
import lk.sliit.itpm.demo.document.User;
import lk.sliit.itpm.demo.dto.TidyServiceDTO;
import lk.sliit.itpm.demo.service.TrackTidyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
//    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ServicesController(TrackTidyService trackTidyService) {
        this.trackTidyService = trackTidyService;
    }

    @PostMapping("create")
    public ResponseEntity<TrackService> createTidyService(
            @AuthenticationPrincipal User user,
            @RequestParam("memberName") @NotNull String memberName,
            @RequestParam("address") @NotNull String address,
            @RequestParam("email") @NotNull String email,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("serviceType") @NotNull String serviceType,
            @RequestParam("serviceDesc") @NotNull String serviceDesc,
            @RequestParam("referralCode") @NotNull String referralCode ) throws ParseException, IOException {

        TidyServiceDTO build = TidyServiceDTO.builder()
                .userId(user.getEmail())
                .memberName(memberName)
                .address(address)
                .email(email)
                .phoneNumber(phoneNumber)
                .serviceType(serviceType)
                .serviceDesc(serviceDesc)
                .referralCode(referralCode)
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
            @RequestParam("memberName") @NotNull String memberName,
            @RequestParam("email") @NotNull String email,
            @RequestParam("serviceType") @NotNull String serviceType,
            @RequestParam("serviceDesc") @NotNull String serviceDesc,
            @RequestParam("phoneNumber") @NotNull String phoneNumber,
            @RequestParam("address") @NotNull String address ) throws ParseException, IOException {

        TrackService service = trackTidyService.getTidServiceById(id);

        if (memberName != null){
            service.setMemberName(memberName);
        }
        service.setEmail(email);
        service.setServiceType(serviceType);
        service.setServiceDesc(serviceDesc);
        service.setPhoneNumber(phoneNumber);
        service.setAddress(address);
        service.setReferralCode(service.getReferralCode());

        TidyServiceDTO build = TidyServiceDTO.builder()
                .memberName(service.getMemberName())
                .email(service.getEmail())
                .serviceType(service.getServiceType())
                .serviceDesc(service.getServiceDesc())
                .phoneNumber(service.getPhoneNumber())
                .address(service.getAddress())
                .build();

        return ResponseEntity.status(201).body(trackTidyService.updateTidyService(id, build));

    }
}
