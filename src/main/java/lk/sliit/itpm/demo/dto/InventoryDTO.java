package lk.sliit.itpm.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private String productId;

    @NotNull(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @Positive(message = "Product value must be positive")
    private double productValue;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private Date purchaseDate;

    @PositiveOrZero(message = "Warranty period cannot be negative")
    private int warrantyPeriod;

    @NotBlank(message = "Product category is required")
    private String productCategory;

    private String productImage;
    
}
