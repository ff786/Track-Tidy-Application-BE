package lk.sliit.itpm.demo.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackService {

    @Id
    private String id;
    private String serviceType;
    private String serviceDesc;
    private String memberId;
    private String memberName;
    private String address;
    private String email;
    private String phoneNumber;
    private String referralCode;
    private User approvedBy;

}
