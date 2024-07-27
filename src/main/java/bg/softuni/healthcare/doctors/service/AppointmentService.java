package bg.softuni.healthcare.doctors.service;

import bg.softuni.healthcare.doctors.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.doctors.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.doctors.model.dto.UserAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    void bookAppointment(AddAppointmentDTO appointmentDTO);

    List<AddAppointmentDTO> getAllAppointments();

    void deleteAppointment(Long id);

    List<UserAppointmentDTO> getUsersAppointments();

    List<FullAppointmentsInfoDTO> getAllUsersAppointments();

    List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date);
}
