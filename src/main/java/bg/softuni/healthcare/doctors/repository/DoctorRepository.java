package bg.softuni.healthcare.doctors.repository;

import bg.softuni.healthcare.doctors.model.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByTown(String town);

    @Query("SELECT DISTINCT d.town FROM DoctorEntity d")
    List<String> findAllTowns();

    @Query("SELECT d FROM DoctorEntity d WHERE LOWER(CONCAT(d.firstName, ' ', d.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<DoctorEntity> findByNameContainingIgnoreCase(@Param("name") String name);
}
