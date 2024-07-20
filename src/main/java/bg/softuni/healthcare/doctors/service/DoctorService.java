package bg.softuni.healthcare.doctors.service;

import bg.softuni.healthcare.doctors.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.AllDoctorsDTO;
import bg.softuni.healthcare.doctors.model.dto.InfoDoctorDTO;

import java.util.List;

public interface DoctorService {

    void addDoctor(AddDoctorDTO addDoctorDTO);

    List<AllDoctorsDTO> getAllDoctors();

    InfoDoctorDTO getDoctorInfo(Long doctorId);

}
