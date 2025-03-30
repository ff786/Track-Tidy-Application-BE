package lk.sliit.itpm.demo.dto;

import jakarta.validation.constraints.NotNull;
import lk.sliit.itpm.demo.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String mobileNumber;

    private Role role;

}


