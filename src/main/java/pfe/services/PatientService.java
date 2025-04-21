package pfe.services;

import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {


    PatientDto addPatient (PatientDto patientDto);
    List<PatientDto> getAllPatient();
    Optional<PatientDto> getPatientById(Long id);
    void deletePatient(Long id);
    PatientDto updatePatient (PatientDto patientdto);

}
