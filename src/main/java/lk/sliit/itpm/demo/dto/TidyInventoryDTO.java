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
public class TidyInventoryDTO {

    private String id;
    private String userId;
    private String productName;
    private String productId;
    private int quantity;
    private int WarrantyPeriod;
    private int productValue;
    private String productCategory;
    private byte[] ProductImage;
    private String productImageBase64;
    private String faulted;
    private Date requestDate;

}
