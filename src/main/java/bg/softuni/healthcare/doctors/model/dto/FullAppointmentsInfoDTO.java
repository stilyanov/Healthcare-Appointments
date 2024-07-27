package bg.softuni.healthcare.doctors.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullAppointmentsInfoDTO {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String reason;
    private String time;

}
