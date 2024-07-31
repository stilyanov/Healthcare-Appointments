package bg.softuni.healthcare.appointments.model.dto;

import bg.softuni.healthcare.appointments.model.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorAppointmentDTO {

    private Long patientId;
    private DepartmentEnum department;
    private String dateTime;
    private String reason;

}
