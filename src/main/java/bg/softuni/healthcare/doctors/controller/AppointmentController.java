package bg.softuni.healthcare.doctors.controller;

import bg.softuni.healthcare.doctors.model.dto.AppointmentDTO;
import bg.softuni.healthcare.doctors.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.doctors.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/book/{doctorId}")
    public List<LocalDateTime> getAvailableAppointmentTimes(@PathVariable("doctorId") Long doctorId,
                                                            @RequestParam(value = "date", required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAvailableAppointmentTimes(doctorId, date != null ? date : LocalDate.now());
    }

    @GetMapping
    public ResponseEntity<List<UserAppointmentDTO>> getUsersAppointments(Long userId, Long doctorId, Long departmentId) {
        return ResponseEntity.ok(
                appointmentService.getUsersAppointments(userId, doctorId, departmentId)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}