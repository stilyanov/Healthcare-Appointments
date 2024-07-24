package bg.softuni.healthcare.doctors.service.impl;

import bg.softuni.healthcare.doctors.model.dto.*;
import bg.softuni.healthcare.doctors.model.entity.DoctorEntity;
import bg.softuni.healthcare.doctors.model.enums.DepartmentEnum;
import bg.softuni.healthcare.doctors.repository.DoctorRepository;
import bg.softuni.healthcare.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public DoctorDTO addDoctor(AddDoctorDTO addDoctorDTO) {
        ResponseEntity<UserDTO> userResponse = restTemplate.getForEntity("http://localhost:8080/users/" + addDoctorDTO.getUserId(), UserDTO.class);
        if (userResponse.getBody() == null) {
            throw new IllegalArgumentException("User not found");
        }

        ResponseEntity<DepartmentDTO> departmentResponse = restTemplate.getForEntity("http://localhost:8080/departments/" + addDoctorDTO.getDepartment().name(), DepartmentDTO.class);
        if (departmentResponse.getBody() == null) {
            throw new IllegalArgumentException("Department not found");
        }
        DoctorEntity doctor = modelMapper.map(addDoctorDTO, DoctorEntity.class);
        doctor.setUserId(userResponse.getBody().getId());
        doctor.setDepartment(DepartmentEnum.valueOf(addDoctorDTO.getDepartment().name()));
        this.doctorRepository.save(doctor);
        return null;
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DoctorDTO.class))
                .toList();
    }

    @Override
    public InfoDoctorDTO getDoctorInfo(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .map(d -> {
                    InfoDoctorDTO map = modelMapper.map(d, InfoDoctorDTO.class);
                    map.setDepartment(d.getDepartment().name());
                    return map;
                })
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Override
    public List<DoctorDTO> findDoctor(String department, String town, String name) {
        DepartmentEnum departmentEnum = DepartmentEnum.valueOf(department.toUpperCase());
        List<DoctorEntity> doctors = doctorRepository.findDoctors(departmentEnum, town, name);
        return doctors.stream()
                .map(d -> {
                    DoctorDTO map = modelMapper.map(d, DoctorDTO.class);
                    map.setDepartment(departmentEnum);
                    return map;
                })
                .toList();
    }
}
