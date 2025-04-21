package pfe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pfe.dto.MedecinDto;
import pfe.entities.Medecin;
import pfe.repository.MedecinRepository;
import pfe.services.MedecinService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;

    @Override
    public MedecinDto addMedecin(MedecinDto medecinDto) {
        Medecin medecin = MedecinDto.toEntity(medecinDto);
        medecin = medecinRepository.save(medecin);
        return MedecinDto.toDto(medecin);
    }


    @Override
    public List<MedecinDto> getAllMedecin() {
        return medecinRepository.findAll().stream()
                .map(MedecinDto::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<MedecinDto> getMedecinById(Long id) {
        return medecinRepository.findById(id).map(MedecinDto::toDto);
    }



    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }


    @Override
    public MedecinDto updateMedecin(MedecinDto medecinDto) {
        Medecin existing = medecinRepository.findById(medecinDto.getId())
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));

        existing.setFirstName(medecinDto.getFirstName());
        existing.setLastName(medecinDto.getLastName());
        existing.setEmail(medecinDto.getEmail());
        existing.setPassword(medecinDto.getPassword());
        existing.setAdress(medecinDto.getAdress());
        existing.setPhone(medecinDto.getPhone());
        existing.setSpecialite(medecinDto.getSpecialite());


        medecinRepository.save(existing);
        return MedecinDto.toDto(existing);
    }

}
