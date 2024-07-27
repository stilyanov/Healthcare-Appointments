package bg.softuni.healthcare.doctors.service;

import bg.softuni.healthcare.doctors.model.dto.AppointmentDTO;
import bg.softuni.healthcare.doctors.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.doctors.model.dto.UserAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    void bookAppointment(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> getAllAppointments();

    List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo(Long doctorId, Long patientId);

    void deleteAppointment(Long id);

    List<UserAppointmentDTO> getUsersAppointments(Long userId, Long doctorId, Long departmentId);

    List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date);
}
