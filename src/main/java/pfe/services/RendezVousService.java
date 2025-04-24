package pfe.services;

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


    RendezVous demanderRendezVous(Long patientId, Long medecinId, String motif, Date dateProposee);
    List<RendezVous> getDemandesPourMedecin(Long medecinId);
    RendezVous validerRendezVous(Long rdvId, Date dateFinale);

}
