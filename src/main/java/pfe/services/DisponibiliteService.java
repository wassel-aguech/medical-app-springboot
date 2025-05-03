package pfe.services;

import pfe.dto.DisponibiliteDto;
import pfe.dto.MedecinDto;

import java.util.List;

public interface DisponibiliteService {


    DisponibiliteDto addDisponibilite (DisponibiliteDto disponibiliteDto);

    List<DisponibiliteDto> getAllDisponibilite();

    void deleteDisponibilite( Long id);

    DisponibiliteDto updateDisponibilite (DisponibiliteDto disponibiliteDto);

    List<DisponibiliteDto> getDisponibilitesByMedecinId(Long medecinId);

}
