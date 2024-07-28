package bg.softuni.healthcare.appointments.service.impl;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.appointments.model.entity.Appointment;
import bg.softuni.healthcare.appointments.repository.AppointmentRepository;
import bg.softuni.healthcare.appointments.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void bookAppointment(AddAppointmentDTO appointmentDTO) {
        LocalDateTime appointmentDateTime = appointmentDTO.getDateTime();
        if (!isValidAppointmentTime(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment must be on a weekday between 08:00 and 17:00.");
        }

        if (isAppointmentTimeTaken(appointmentDTO.getDoctorId(), appointmentDateTime)) {
            throw new IllegalArgumentException("This appointment time is already taken.");
        }

        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);

        appointmentRepository.save(appointment);
    }

    @Override
    public List<UserAppointmentDTO> getUserAppointments(Long userId) {
        return appointmentRepository.findAllByPatientId(userId)
                .stream()
                .map(appointment -> {
                    UserAppointmentDTO userAppointmentDTO = modelMapper.map(appointment, UserAppointmentDTO.class);
                    userAppointmentDTO.setDateTime(String.valueOf(appointment.getDateTime()));
                    return userAppointmentDTO;
                })
                .toList();
    }

    @Override
    public List<FullAppointmentsInfoDTO> getAllUsersAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> {
                    FullAppointmentsInfoDTO fullAppointmentsInfoDTO = modelMapper.map(appointment, FullAppointmentsInfoDTO.class);
                    fullAppointmentsInfoDTO.setDateTime(String.valueOf(appointment.getDateTime()));
                    return fullAppointmentsInfoDTO;
                })
                .toList();
    }

    @Override
    public List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date) {
        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        LocalDateTime now = LocalDateTime.now();

        while (startTime.isBefore(endTime)) {
            LocalDateTime slot = LocalDateTime.of(date, startTime);
            if (!isAppointmentTimeTaken(doctorId, slot) && slot.isAfter(now)) {
                availableSlots.add(slot);
            }
            startTime = startTime.plusMinutes(30);
        }
        return availableSlots;
    }

    @Override
    public void deleteAppointment(Long id) {
        this.appointmentRepository.deleteById(id);
    }


    private boolean isAppointmentTimeTaken(Long doctorId, LocalDateTime appointmentDateTime) {
        return appointmentRepository.findByDoctorIdAndDateTime(doctorId, appointmentDateTime).isPresent();
    }

    private boolean isValidAppointmentTime(LocalDateTime appointmentDateTime) {
        DayOfWeek day = appointmentDateTime.getDayOfWeek();
        LocalTime time = appointmentDateTime.toLocalTime();
        return isWeekday(day) && isWithinWorkingHours(time);
    }

    private boolean isWeekday(DayOfWeek day) {
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private boolean isWithinWorkingHours(LocalTime time) {
        LocalTime startTime = LocalTime.of(8, 0); // 08:00
        LocalTime endTime = LocalTime.of(17, 0); // 17:00
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }
}
