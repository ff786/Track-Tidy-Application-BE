package lk.sliit.itpm.demo.dto;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String token;
    private String newPassword;
    
}
