package pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pfe.dto.DemandeRendezVousDto;
import pfe.dto.RendezVousDto;
import pfe.dto.ReponseRendezVousDto;
import pfe.dto.ValidationDto;
import pfe.entities.RendezVous;
import pfe.entities.statusRendezVous;
import pfe.repository.RendezVousRepository;
import pfe.services.RendezVousService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/rendezvous")
@RequiredArgsConstructor

public class RendezVousController {

    private final RendezVousService rendezVousService;
    private final RendezVousRepository rendezVousRepository;

    @PostMapping("/addRendezVous")
    public ResponseEntity<RendezVousDto> addRendezVous(Authentication connectedUser  , @RequestBody RendezVousDto rendezVousDto) {
        RendezVousDto newRendezVous = rendezVousService.DemandeRendezVous(connectedUser,rendezVousDto);
        return ResponseEntity.ok(newRendezVous);
    }


    @GetMapping("/medecin/{id}")
    public ResponseEntity<List<RendezVous>> getDemandes(@PathVariable Long id) {
        return ResponseEntity.ok(rendezVousService.getDemandesPourMedecin(id));
    }


    @GetMapping("/getrendezvousbypatientid/{patientId}")
    public ResponseEntity<List<RendezVousDto>> getRendezVousByPatient(@PathVariable Long patientId) {
        List<RendezVousDto> rendezVousList = rendezVousService.getRendezVousByPatientId(patientId);
        return ResponseEntity.ok(rendezVousList);
    }



    @GetMapping("/getrendezvousbymedecinid/{medecinId}")
    public ResponseEntity<List<RendezVousDto>> getRendezVousByMedecin(@PathVariable Long medecinId) {
        List<RendezVousDto> rendezVousList = rendezVousService.getRendezVousByMedecinId(medecinId);
        return ResponseEntity.ok(rendezVousList);
    }




    @PutMapping("/rendezvous/{id}/repondre")
    public ResponseEntity<RendezVousDto> repondreRendezVous(
            @PathVariable Long id,
            @RequestBody ReponseRendezVousDto reponseDto
    ) {
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow();
        rendezVous.setDateRendezVous(reponseDto.getDateRendezVous());
        rendezVous.setMotif(reponseDto.getMotif());
        rendezVous.setStatut(statusRendezVous.VALIDE);

        RendezVous updated = rendezVousRepository.save(rendezVous);
        return ResponseEntity.ok(RendezVousDto.toDto(updated));
    }


    @GetMapping("/valide/patient/{id}")
    public List<RendezVousDto> getRendezVousValides(@PathVariable Long id) {
        return rendezVousService.getRendezVousValidesByPatient(id);
    }


    @GetMapping("/rendezvoustoday/{medecinId}")
    public ResponseEntity<List<RendezVousDto>> getTodayRendezVousByMedecin(@PathVariable Long medecinId) {
        List<RendezVousDto> result = rendezVousService.getRendezVousTodayByMedecin(medecinId);
        return ResponseEntity.ok(result);
    }







}
