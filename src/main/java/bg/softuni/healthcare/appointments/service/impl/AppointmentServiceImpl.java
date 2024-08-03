package bg.softuni.healthcare.appointments.service.impl;

import bg.softuni.healthcare.appointments.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.DoctorAppointmentDTO;
import bg.softuni.healthcare.appointments.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.appointments.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.appointments.model.entity.Appointment;
import bg.softuni.healthcare.appointments.repository.AppointmentRepository;
import bg.softuni.healthcare.appointments.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserAppointmentDTO bookAppointment(AddAppointmentDTO appointmentDTO) {
        LocalDateTime appointmentDateTime = appointmentDTO.getDateTime();
        if (!isValidAppointmentTime(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment must be on a weekday between 08:00 and 17:00.");
        }

        if (isAppointmentTimeTaken(appointmentDTO.getDoctorId(), appointmentDateTime)) {
            throw new IllegalArgumentException("This appointment time is already taken.");
        }

        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        appointmentRepository.save(appointment);
        return modelMapper.map(appointment, UserAppointmentDTO.class);
    }

    @Override
    public List<UserAppointmentDTO> getUserAppointments(Long userId) {
        return appointmentRepository.findAllByPatientId(userId)
                .stream()
                .map(this::mapToUserAppointmentDTO)
                .toList();
    }

    @Override
    public DoctorAppointmentDTO getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .map(this::mapToDoctorAppointmentDTO)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
    }

    @Override
    public List<FullAppointmentsInfoDTO> getAllUsersAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToFullAppointmentsDTO)
                .toList();
    }

    @Override
    public List<UserAppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);
        return appointments.stream()
                .map(this::mapToUserAppointmentDTO)
                .toList();
    }

    @Override
    public List<DoctorAppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findAllByDoctorId(doctorId);
        return appointments.stream()
                .map(this::mapToDoctorAppointmentDTO)
                .toList();
    }

    @Override
    @Scheduled(cron = "0 0 1 * *") // At 00:00 on day-of-month 1
    public void removePastAppointments() {
        Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);
        logger.info("Starting to remove past appointments.");

        List<Appointment> appointments = appointmentRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        List<Appointment> pastAppointments = appointments.stream()
                .filter(appointment -> appointment.getDateTime().isBefore(now))
                .toList();

        long countBefore = appointments.size();
        long countAfter = pastAppointments.size();

        pastAppointments.forEach(appointment -> {
            logger.info("Removing past appointment with ID: {}", appointment.getId());
            appointmentRepository.delete(appointment);
        });

        logger.info("Completed removal of past appointments. Deleted {} out of {} appointments.", countAfter, countBefore);
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

    private UserAppointmentDTO mapToUserAppointmentDTO(Appointment appointment) {
        UserAppointmentDTO userAppointmentDTO = modelMapper.map(appointment, UserAppointmentDTO.class);
        userAppointmentDTO.setDateTime(String.valueOf(appointment.getDateTime()));
        return userAppointmentDTO;
    }

    private FullAppointmentsInfoDTO mapToFullAppointmentsDTO(Appointment appointment) {
        FullAppointmentsInfoDTO fullAppointmentsInfoDTO = modelMapper.map(appointment, FullAppointmentsInfoDTO.class);
        fullAppointmentsInfoDTO.setDateTime(String.valueOf(appointment.getDateTime()));
        return fullAppointmentsInfoDTO;
    }

    private DoctorAppointmentDTO mapToDoctorAppointmentDTO(Appointment appointment) {
        DoctorAppointmentDTO doctorAppointmentDTO = modelMapper.map(appointment, DoctorAppointmentDTO.class);
        doctorAppointmentDTO.setDateTime(String.valueOf(appointment.getDateTime()));
        return doctorAppointmentDTO;
    }
}
