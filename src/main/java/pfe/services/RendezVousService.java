package pfe.services;

import org.springframework.security.core.Authentication;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.dto.RendezVousDto;
import pfe.entities.RendezVous;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RendezVousService {

    RendezVousDto addRendezVous (RendezVousDto rendezVousDto);
    List<RendezVousDto> getAllRendezVous();
    Optional<RendezVousDto> getRendezVousById(Long id);
    void deleteRenderVous(Long id);

    RendezVousDto DemandeRendezVous(Authentication connectedUser, RendezVousDto rendezVousDto);
    List<RendezVous> getDemandesPourMedecin(Long medecinId);
    //RendezVous validerRendezVous(Long rdvId, Date dateFinale);



    List<RendezVousDto> getRendezVousByPatientId(Long patientId);
    List<RendezVousDto> getRendezVousByMedecinId(Long medecinId);



    List<RendezVousDto> getRendezVousValidesByPatient(Long patientId);

}
