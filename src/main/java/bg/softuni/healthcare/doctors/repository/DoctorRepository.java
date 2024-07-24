package bg.softuni.healthcare.doctors.repository;

import bg.softuni.healthcare.doctors.model.entity.DoctorEntity;
import bg.softuni.healthcare.doctors.model.enums.DepartmentEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    @Query("SELECT d FROM DoctorEntity d WHERE "
            + "(:department IS NULL OR d.department = :department) AND "
            + "(:town IS NULL OR d.town = :town) AND "
            + "(:name IS NULL OR (d.firstName LIKE %:name% OR d.lastName LIKE %:name%))")
    List<DoctorEntity> findDoctors(@Param("department") DepartmentEnum department,
                                   @Param("town") String town,
                                   @Param("name") String name);
}