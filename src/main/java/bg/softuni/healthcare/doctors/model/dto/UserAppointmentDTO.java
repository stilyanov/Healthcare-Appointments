package bg.softuni.healthcare.doctors.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAppointmentDTO {

    private Long doctorId;
    private Long departmentId;
    private String time;
    private String reason;
}
