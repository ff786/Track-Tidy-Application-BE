package lk.sliit.itpm.demo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackGroceryRequest {

    @Id
    private String id;
    private String userId;
    private String itemName;
    private String productId;
    private int quantity;
    private int price;
    private Date expiryDate;
    private byte[] itemImage;

    private boolean isApproved = false;
    private User approvedBy;
}
