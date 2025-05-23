package lk.sliit.itpm.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackInventoryResponseDTO {

    private String id;
    private String productId;
    private String productName;
    private String productCategory;
    private int productValue;
    private int WarrantyPeriod;
    private int quantity;
    private String faulted;
    private Date requestDate;
    private String productImageBase64;
}

