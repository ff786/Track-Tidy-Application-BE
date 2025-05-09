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
public class TidyPackageDTO {

    private String id;
    private String userId;
    private String packageType;
    private int packageValue;
    private int groceryValue;
    private int serviceValue;
    private int inventoryValue;
    private Date subscribeDate;
}
