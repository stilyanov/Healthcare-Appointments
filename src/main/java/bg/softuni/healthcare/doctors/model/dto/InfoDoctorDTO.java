package bg.softuni.healthcare.doctors.model.dto;

import bg.softuni.healthcare.doctors.model.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoDoctorDTO {
    private Long id;

    private String imageUrl;

    private String firstName;

    private String lastName;

    private String bio;

    private String town;

    private String department;

    private Integer experience;

    private String userId;

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName);
        }

        if (lastName != null && !lastName.isEmpty()) {
            sb.append(" ").append(lastName);
        }

        return sb.toString();
    }
}
