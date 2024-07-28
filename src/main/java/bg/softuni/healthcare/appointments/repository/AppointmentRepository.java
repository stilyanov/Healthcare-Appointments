package bg.softuni.healthcare.appointments.repository;

import bg.softuni.healthcare.appointments.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByPatientId(Long patient);

    Optional<Appointment> findByDoctorIdAndDateTime(Long doctorId, LocalDateTime dateTime);

}
