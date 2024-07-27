package bg.softuni.healthcare.doctors.controller;

import bg.softuni.healthcare.doctors.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.doctors.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.doctors.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<AddAppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }

    @GetMapping("/all/{patientId}/{doctorId}/{departmentId}")
    public ResponseEntity<List<UserAppointmentDTO>> getUsersAppointments(@PathVariable Long patientId, @PathVariable Long doctorId, @PathVariable Long departmentId) {
        return ResponseEntity.ok(
                appointmentService.getUsersAppointments()
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