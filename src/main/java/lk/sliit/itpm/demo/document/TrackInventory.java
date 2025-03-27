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
public class TrackInventory {

    @Id
    private String id;
    private String userId;
    private String productName;
    private String productId;
    private int quantity;
    private Date purchaseDate;
    private int productValue;
    private Date warrantyDate;
    private String productCategory;
    private byte[] ProductImage;
    private String Faulted;
    private User approvedBy;

}
