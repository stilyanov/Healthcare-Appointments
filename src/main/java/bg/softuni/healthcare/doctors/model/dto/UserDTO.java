package bg.softuni.healthcare.doctors.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}
