package pfe.services;

import pfe.dto.MedecinDto;

import java.util.List;
import java.util.Optional;

public interface MedecinService {

    MedecinDto addMedecin (MedecinDto medecinDto);
    List<MedecinDto> getAllMedecin();
    Optional<MedecinDto> getMedecinById(Long id);
    void deleteMedecin( Long id);
    MedecinDto updateMedecin (MedecinDto medecinDto);

}
