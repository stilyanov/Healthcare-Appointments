package bg.softuni.healthcare.doctors.controller;

import bg.softuni.healthcare.doctors.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.doctors.model.dto.InfoDoctorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final

    @PostMapping
    public ResponseEntity<InfoDoctorDTO> addDoctor(AddDoctorDTO addDoctorDTO) {

    }

}
