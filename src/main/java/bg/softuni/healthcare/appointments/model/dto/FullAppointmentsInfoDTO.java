package bg.softuni.healthcare.appointments.model.dto;

import bg.softuni.healthcare.appointments.model.enums.DepartmentEnum;
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
    private DepartmentEnum department;
    private String reason;
    private String dateTime;

}
