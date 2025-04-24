package pfe.services;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.dto.RendezVousDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {


    PatientDto addPatient (PatientDto patientDto);
    List<PatientDto> getAllPatient();
    Optional<PatientDto> getPatientById(Long id);
    void deletePatient(Long id);
    PatientDto updatePatient (PatientDto patientdto);
    PatientDto uploadPaientImage(Long IdPatient, MultipartFile image);






}
