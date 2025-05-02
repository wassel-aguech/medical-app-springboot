package pfe.services.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pfe.configImage.ImageStorage;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.dto.RendezVousDto;
import pfe.entities.Medecin;
import pfe.entities.Patient;
import pfe.entities.RendezVous;
import pfe.repository.PatientRepository;
import pfe.repository.RendezVousRepository;
import pfe.services.PatientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl  implements PatientService {

    private final PatientRepository patientRepository;
    private final ImageStorage imageStorage;
    private final RendezVousRepository rendezVousRepository;


    @Override
    public PatientDto addPatient(PatientDto patientdto) {
        Patient patient = PatientDto.toEntity(patientdto);
        patient = patientRepository.save(patient);
        return PatientDto.toDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatient() {
        return patientRepository.findAll().stream()
                .map(PatientDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDto> getPatientById(Long id) {
        return patientRepository.findById(id).map(PatientDto::toDto);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientdto) {
        Patient patient = PatientDto.toEntity(patientdto);
        patient = patientRepository.save(patient);
        return PatientDto.toDto(patient);
    }



    public ResponseEntity<Patient> findbyId(Long id) {
        if (id == null) {
            return null;
        }
        return ResponseEntity.ok(patientRepository.findById(id).get());

    }

    @Override
    public PatientDto uploadPaientImage(Long idPatient, MultipartFile image) {

        ResponseEntity<Patient> patientResponseEntity = this.findbyId(idPatient);
        String imageName=imageStorage.store(image);

        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/patients/downloadpatientimage/").path(imageName).toUriString();

        Patient patient = patientResponseEntity.getBody();

        if (patient!=null)
            patient.setImage(fileImageDownloadUrl);

        Patient patientsaved = patientRepository.save(patient);
        new PatientDto();
        return  PatientDto.toDto(patientsaved);
    }























}
