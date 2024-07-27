package bg.softuni.healthcare.doctors.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;

    @NotNull(message = "Time cannot be empty!")
    private LocalDateTime dateTime;

    @NotNull(message = "Reason cannot be empty!")
    @Size(min = 10, max = 500, message = "Reason length must be between 10 and 200 characters!")
    private String reason;

    private Long doctorId;
    private Long patientId;
}
