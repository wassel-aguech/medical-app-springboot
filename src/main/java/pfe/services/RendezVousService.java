package pfe.services;

import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.dto.RendezVousDto;

import java.util.List;
import java.util.Optional;

public interface RendezVousService {

    RendezVousDto addRendezVous (RendezVousDto rendezVousDto);
    List<RendezVousDto> getAllRendezVous();
    Optional<RendezVousDto> getRendezVousById(Long id);
    void deleteRenderVous(Long id);


}
