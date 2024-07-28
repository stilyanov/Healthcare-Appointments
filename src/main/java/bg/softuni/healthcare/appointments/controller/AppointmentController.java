package bg.softuni.healthcare.appointments.controller;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.appointments.model.enums.DepartmentEnum;
import bg.softuni.healthcare.appointments.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/book/{doctorId}")
    public ResponseEntity<List<LocalDateTime>> getAvailableAppointmentTimes(@PathVariable Long doctorId, @RequestParam LocalDate date) {
        List<LocalDateTime> availableSlots = appointmentService.getAvailableAppointmentTimes(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserAppointmentDTO>> userAppointments(@PathVariable Long userId) {
        return ResponseEntity.ok(
                appointmentService.getUserAppointments(userId)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<FullAppointmentsInfoDTO>> allUsersAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllUsersAppointments()
        );
    }

    @PostMapping("/book/{doctorId}")
    public ResponseEntity<UserAppointmentDTO> bookAppointment(@RequestBody AddAppointmentDTO appointmentDTO,
                                                              @PathVariable Long doctorId) {
        appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}