package bg.softuni.healthcare.doctors.service.impl;

import bg.softuni.healthcare.doctors.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.AllDoctorsDTO;
import bg.softuni.healthcare.doctors.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.doctors.repository.DoctorRepository;
import bg.softuni.healthcare.doctors.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void addDoctor(AddDoctorDTO addDoctorDTO) {

    }

    @Override
    public List<AllDoctorsDTO> getAllDoctors() {
        return List.of();
    }

    @Override
    public InfoDoctorDTO getDoctorInfo(Long doctorId) {
        return null;
    }
}
