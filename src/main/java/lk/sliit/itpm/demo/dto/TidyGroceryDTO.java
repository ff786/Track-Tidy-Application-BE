package lk.sliit.itpm.demo.dto;

import lk.sliit.itpm.demo.document.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TidyGroceryDTO {

    private String id;
    private String itemName;
    private String userId;
    private String productId;
    private int quantity;
    private int price;
    private Date expiryDate;
    private byte[] itemImage;
    private User approvedBy;
    private String productImageBase64;
}
