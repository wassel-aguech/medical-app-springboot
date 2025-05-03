package pfe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pfe.dto.DisponibiliteDto;
import pfe.dto.MedecinDto;
import pfe.entities.Disponibilite;
import pfe.entities.Medecin;
import pfe.entities.Role;
import pfe.repository.DisponibiliteRepository;
import pfe.repository.MedecinRepository;
import pfe.services.DisponibiliteService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisponibilireServiceImpl implements DisponibiliteService {

    private final DisponibiliteRepository disponibiliteRepository;
    private final MedecinRepository medecinRepository;



    @Override
    public DisponibiliteDto addDisponibilite(DisponibiliteDto disponibiliteDto) {

        Medecin medecin = medecinRepository.findById(disponibiliteDto.getMedecinId())
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé  "));


        Disponibilite disponibilite = DisponibiliteDto.toEntity(disponibiliteDto);
        disponibilite.setMedecin(medecin);

        disponibilite = disponibiliteRepository.save(disponibilite);

        return DisponibiliteDto.toDto(disponibilite);
    }



    @Override
    public List<DisponibiliteDto> getAllDisponibilite() {
        return disponibiliteRepository.findAll().stream()
                .map(DisponibiliteDto::toDto)
                .collect(Collectors.toList());
    }




    @Override
    public void deleteDisponibilite(Long id) {
        disponibiliteRepository.deleteById(id);
    }



    @Override
    public DisponibiliteDto updateDisponibilite(DisponibiliteDto disponibiliteDto) {
        Disponibilite existing = disponibiliteRepository.findById(disponibiliteDto.getId())
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));

        existing.setJour(disponibiliteDto.getJour());
        existing.setHeureDebut(disponibiliteDto.getHeureDebut());
        existing.setHeureFin(disponibiliteDto.getHeureFin());

        disponibiliteRepository.save(existing);
        return DisponibiliteDto.toDto(existing);
    }


    @Override
    public List<DisponibiliteDto> getDisponibilitesByMedecinId(Long medecinId) {
        List<Disponibilite> disponibilites = disponibiliteRepository.findByMedecinId(medecinId);
        return disponibilites.stream()
                .map(DisponibiliteDto::toDto)
                .collect(Collectors.toList());
    }




}
