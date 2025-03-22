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
public class TidyGroceryDTO {

    private String firstName;
    private String lastName;
    private String memberId;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String email;
    private String serviceType;
    private int amount;
    private String diagnosisId;
    private Date treatmentDate;
    private byte[] receipt;
}
