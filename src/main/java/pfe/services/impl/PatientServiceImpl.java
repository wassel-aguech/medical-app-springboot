package pfe.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.entities.Medecin;
import pfe.entities.Patient;
import pfe.repository.PatientRepository;
import pfe.services.PatientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl  implements PatientService {

    private final PatientRepository patientRepository;

    
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
}
