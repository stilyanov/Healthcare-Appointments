package bg.softuni.healthcare.doctors.service;

import bg.softuni.healthcare.doctors.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.DoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {

    DoctorDTO addDoctor(AddDoctorDTO addDoctorDTO);

    List<DoctorDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);

    List<DoctorDTO> findDoctor(String department, String town, String name);

}
