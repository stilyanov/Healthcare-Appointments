package bg.softuni.healthcare.doctors.service;

import bg.softuni.healthcare.doctors.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.DoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {

    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<DoctorDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);

    List<String> getAllTowns();

    void deleteDoctor(Long id);

    List<DoctorDTO> findByName(String name);
}
