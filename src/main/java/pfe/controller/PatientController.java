package pfe.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pfe.dto.PatientDto;
import pfe.services.PatientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @PostMapping("addPatient")
    public PatientDto addPatient(@RequestBody PatientDto patientDto) {
        return patientService.addPatient(patientDto);
    }

    @GetMapping("getall")
    public List<PatientDto> getAllPatient() {
        return patientService.getAllPatient();
    }
   @GetMapping("getPatientById/{id}")
    public Optional<PatientDto> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("deletePatient/{id}")
    public void deletePatient( @PathVariable Long id) {
        patientService.deletePatient(id);
    }


    @PutMapping("updatePatient")
    public PatientDto updatePatient(@RequestBody PatientDto patientdto) {
        return patientService.updatePatient(patientdto);
    }
}
