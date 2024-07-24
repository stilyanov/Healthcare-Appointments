package bg.softuni.healthcare.doctors.model.entity;

import bg.softuni.healthcare.doctors.model.enums.DepartmentEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private Integer experience;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String town;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartmentEnum department;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
