package bg.softuni.healthcare.appointments.controller;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.DoctorAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.appointments.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<LocalDateTime>> getAvailableAppointmentTimes(@PathVariable Long doctorId, @RequestParam LocalDate date) {
        List<LocalDateTime> availableSlots = appointmentService.getAvailableAppointmentTimes(doctorId, date);
        return ResponseEntity.ok(availableSlots);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAppointmentDTO>> userAppointments(@PathVariable Long userId) {
        return ResponseEntity.ok(
                appointmentService.getUserAppointments(userId)
        );
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<DoctorAppointmentDTO> getAppointmentById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentById(appointmentId)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<UserAppointmentDTO>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByPatientId(patientId)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorAppointmentDTO>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(
                appointmentService.getAppointmentsByDoctorId(doctorId)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<FullAppointmentsInfoDTO>> allUsersAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllUsersAppointments()
        );
    }

    @PostMapping("/book/{doctorId}")
    public ResponseEntity<UserAppointmentDTO> bookAppointment(@Valid @RequestBody AddAppointmentDTO appointmentDTO,
                                                              @PathVariable Long doctorId,
                                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Invalid appointment data.");
        }

        UserAppointmentDTO response = appointmentService.bookAppointment(appointmentDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @DeleteMapping("/doctor/{doctorId}")
    public ResponseEntity<Void> deleteAppointmentsByDoctor(@PathVariable Long doctorId) {
        appointmentService.deleteAppointmentsByDoctorId(doctorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<Void> deleteAppointmentsByPatient(@PathVariable Long patientId) {
        appointmentService.deleteAppointmentsByPatientId(patientId);
        return ResponseEntity.noContent().build();
    }

}