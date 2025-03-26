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
public class TrackGrocery {

    @Id
    private String id;
    private String itemName;
    private String memberId;
    private String phoneNumber;
    private int price;
    private Date purchaseDate;
    private Date expiryDate;
    private byte[] itemImage;
    private User approvedBy;
}
