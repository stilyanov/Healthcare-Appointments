package bg.softuni.healthcare.appointments.service;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.DoctorAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {


    UserAppointmentDTO bookAppointment(AddAppointmentDTO appointmentDTO);

    List<UserAppointmentDTO> getUserAppointments(Long userId);

    List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date);

    void deleteAppointment(Long id);

    DoctorAppointmentDTO getAppointmentById(Long appointmentId);

    List<FullAppointmentsInfoDTO> getAllUsersAppointments();

    List<UserAppointmentDTO> getAppointmentsByPatientId(Long patientId);

    List<DoctorAppointmentDTO> getAppointmentsByDoctorId(Long doctorId);

    void removePastAppointments();
}
