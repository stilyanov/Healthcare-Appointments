package bg.softuni.healthcare.doctors.model.dto;

import bg.softuni.healthcare.doctors.model.enums.DepartmentEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AddAppointmentDTO {
    private Long id;

    @NotNull(message = "Time cannot be empty!")
    private LocalDateTime dateTime;

    @NotNull(message = "Reason cannot be empty!")
    @Size(min = 10, max = 500, message = "Reason length must be between 10 and 200 characters!")
    private String reason;

    @NotNull(message = "Doctor ID cannot be empty!")
    private Long doctorId;

    @NotNull(message = "Patient ID cannot be empty!")
    private Long patientId;

    @NotNull(message = "Department cannot be empty!")
    private DepartmentEnum department;
}
