package pfe.services;

import org.springframework.web.multipart.MultipartFile;
import pfe.dto.MedecinDto;
import pfe.dto.RendezVousDto;

import java.util.List;
import java.util.Optional;

public interface MedecinService {

    MedecinDto addMedecin (MedecinDto medecinDto);
    List<MedecinDto> getAllMedecin();
    Optional<MedecinDto> getMedecinById(Long id);
    void deleteMedecin( Long id);
    MedecinDto updateMedecin (MedecinDto medecinDto);

    MedecinDto uploadMedecinImage(Long IdMedecin, MultipartFile image);




}
