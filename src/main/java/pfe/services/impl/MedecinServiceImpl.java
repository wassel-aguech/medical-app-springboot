package pfe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pfe.configImage.ImageStorage;
import pfe.dto.MedecinDto;
import pfe.dto.RendezVousDto;
import pfe.entities.Medecin;
import pfe.entities.RendezVous;
import pfe.entities.Role;
import pfe.repository.MedecinRepository;
import pfe.repository.RendezVousRepository;
import pfe.repository.RoleRepository;
import pfe.services.MedecinService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;
    private final ImageStorage imageStorage;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RendezVousRepository  rendezVousRepository;



    @Override
    public MedecinDto addMedecin(MedecinDto medecinDto) {
        Medecin medecin = MedecinDto.toEntity(medecinDto);

        String encodedPassword = passwordEncoder.encode(medecin.getPassword());
        medecin.setPassword(encodedPassword);

        List<Role> roles = new ArrayList<>();
            Role userRole = roleRepository.findByName("medecin")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        medecin.setRoles(roles);

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

        if (medecinDto.getFirstName() != null) {
            existing.setFirstName(medecinDto.getFirstName());
        }
        if (medecinDto.getLastName() != null) {
            existing.setLastName(medecinDto.getLastName());
        }
        if (medecinDto.getEmail() != null) {
            existing.setEmail(medecinDto.getEmail());
        }
        if (medecinDto.getPassword() != null) {
            existing.setPassword(medecinDto.getPassword());
        }
        if (medecinDto.getAdress() != null) {
            existing.setAdress(medecinDto.getAdress());
        }
        if (medecinDto.getPhone() != null) {
            existing.setPhone(medecinDto.getPhone());
        }
        if (medecinDto.getSpecialite() != null) {
            existing.setSpecialite(medecinDto.getSpecialite());
        }
        if (medecinDto.getStatus() != null) {
            existing.setStatus(medecinDto.getStatus());
        }
        if (medecinDto.getImage() != null) {
            existing.setImage(medecinDto.getImage());
        }

        medecinRepository.save(existing);
        return MedecinDto.toDto(existing);
    }





    public ResponseEntity<Medecin> findbyId(Long id) {
        if (id == null) {
            return null;
        }
        return ResponseEntity.ok(medecinRepository.findById(id).get());

    }

    @Override
    public MedecinDto uploadMedecinImage(Long IdBlog, MultipartFile image) {

        ResponseEntity<Medecin> medecinResponse = this.findbyId(IdBlog);
        String imageName=imageStorage.store(image);

        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/medecins/downloadmedecinimage/").path(imageName).toUriString();

        Medecin medecin = medecinResponse.getBody();

        if (medecin!=null)
            medecin.setImage(fileImageDownloadUrl);

        Medecin blogsaved = medecinRepository.save(medecin);
        new MedecinDto();
        return  MedecinDto.toDto(blogsaved);
    }
























}
