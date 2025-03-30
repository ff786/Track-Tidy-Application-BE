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

    private String userId;
    private String productName;
    private String productId;
    private int quantity;
    private Date purchaseDate;
    private int productValue;
    private Date warrantyDate;
    private String productCategory;
    private byte[] ProductImage;
    private String faulted;

}
