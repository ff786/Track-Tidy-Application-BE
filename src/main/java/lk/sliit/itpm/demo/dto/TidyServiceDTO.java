package lk.sliit.itpm.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TidyServiceDTO {

    private String serviceType;
    private String serviceDesc;
    private String memberId;
    private String memberName;
    private String address;
    private String email;
    private String phoneNumber;
    private String referralCode;

}
