package bg.softuni.healthcare.appointments.controller;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.appointments.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

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

    @PostMapping("/book")
    public ResponseEntity<UserAppointmentDTO> bookAppointment(@RequestBody AddAppointmentDTO appointmentDTO) {
        appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}