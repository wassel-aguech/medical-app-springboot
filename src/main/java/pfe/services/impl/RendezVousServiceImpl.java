package pfe.services.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pfe.dto.MedecinDto;
import pfe.dto.RendezVousDto;
import pfe.entities.Medecin;
import pfe.entities.Patient;
import pfe.entities.RendezVous;
import pfe.repository.MedecinRepository;
import pfe.repository.PatientRepository;
import pfe.repository.RendezVousRepository;
import pfe.services.RendezVousService;
import pfe.entities.statusRendezVous;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousServiceImpl  implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;

    @Override
    public RendezVousDto addRendezVous(RendezVousDto rendezVousDto) {
        RendezVous rendezVous = RendezVousDto.toEntity(rendezVousDto);
        rendezVous = rendezVousRepository.save(rendezVous);
        return RendezVousDto.toDto(rendezVous);
    }

    @Override
    public List<RendezVousDto> getAllRendezVous() {
        return rendezVousRepository.findAll().stream()
                .map(RendezVousDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RendezVousDto> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id).map(RendezVousDto::toDto);
    }

    @Override
    public void deleteRenderVous(Long id) {
        rendezVousRepository.deleteById(id);
    }


    @Override
    @Transactional
    public RendezVousDto DemandeRendezVous(Authentication connectedUser, RendezVousDto rendezVousDto) {
        Patient patient = ((Patient) connectedUser.getPrincipal());
        System.out.println(patient.getId());
        RendezVous rendezVous = RendezVousDto.toEntity(rendezVousDto);
        rendezVous.setPatient(patient);
        rendezVous = rendezVousRepository.save(rendezVous);
        return RendezVousDto.toDto(rendezVous);
    }



    public RendezVous validerRendezVous(Long rdvId, Date dateFinale) {
        RendezVous rdv = rendezVousRepository.findById(rdvId).orElseThrow();
        rdv.setDateRendezVous(dateFinale);
        rdv.setStatut(statusRendezVous.VALIDE); // ici
        return rendezVousRepository.save(rdv);
    }

    public List<RendezVous> getDemandesPourMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId);
    }








}
