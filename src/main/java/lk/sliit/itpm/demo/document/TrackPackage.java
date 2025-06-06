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
public class TrackPackage {

    @Id
    private String id;
    private String userId;
    private String packageType;
    private int packageValue;
    private int groceryValue;
    private int serviceValue;
    private int inventoryValue;
    private Date subscribeDate;
}
