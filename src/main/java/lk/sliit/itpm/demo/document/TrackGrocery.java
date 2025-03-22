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
    private String email;
    private String firstName;
    private String lastName;
    private String memberId;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String serviceType;
    private int amount;
    private byte[] receipt;
    private User approvedBy;
}
