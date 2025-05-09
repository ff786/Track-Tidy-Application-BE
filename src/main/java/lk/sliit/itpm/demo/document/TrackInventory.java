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
    private String productId;
    private String productName;
    private String productCategory;
    private int productValue;
    private int WarrantyPeriod;
    private byte[] ProductImage;
    private int quantity;
    private String faulted;
    private Date warrantyDate;
    private User approvedBy;
    private String productImageBase64;

}
