package bg.softuni.healthcare.doctors.service.impl;

import bg.softuni.healthcare.doctors.model.dto.*;
import bg.softuni.healthcare.doctors.model.entity.DoctorEntity;
import bg.softuni.healthcare.doctors.repository.DoctorRepository;
import bg.softuni.healthcare.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
        doctor.setDepartmentId(departmentResponse.getBody().getId());

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
                    DepartmentDTO departmentDTO = restTemplate.getForEntity("http://localhost:8080/departments/" + d.getDepartmentId(), DepartmentDTO.class).getBody();
                    if (departmentDTO != null) {
                        map.setDepartment(departmentDTO.getName());
                    }
                    return map;
                })
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Override
    public List<String> getAllTowns() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorEntity::getTown)
                .distinct()
                .toList();
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IllegalArgumentException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public List<DoctorDTO> findByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(d -> modelMapper.map(d, DoctorDTO.class))
                .toList();
    }
}
