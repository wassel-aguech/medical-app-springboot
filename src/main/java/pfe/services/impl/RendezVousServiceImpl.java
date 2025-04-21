package pfe.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pfe.dto.MedecinDto;
import pfe.dto.RendezVousDto;
import pfe.entities.Medecin;
import pfe.entities.RendezVous;
import pfe.repository.RendezVousRepository;
import pfe.services.RendezVousService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousServiceImpl  implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;

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












}
