package pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfe.dto.DemandeRendezVousDto;
import pfe.dto.ValidationDto;
import pfe.entities.RendezVous;
import pfe.services.RendezVousService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/rendezvous")
@RequiredArgsConstructor

public class RendezVousController {

    private final RendezVousService rendezVousService;

    @PostMapping("/demander")
    public ResponseEntity<RendezVous> demanderRendezVous(@RequestBody DemandeRendezVousDto dto) {
        RendezVous rdv = rendezVousService.demanderRendezVous(dto.getPatientId(), dto.getMedecinId(), dto.getMotif(), dto.getDateDemande());
        return ResponseEntity.ok(rdv);
    }

    @PutMapping("/valider/{id}")
    public ResponseEntity<RendezVous> validerRendezVous(@PathVariable Long id, @RequestBody ValidationDto dto) {
        RendezVous rdv = rendezVousService.validerRendezVous(id, dto.getDateRendezVous());
        return ResponseEntity.ok(rdv);
    }

    @GetMapping("/medecin/{id}")
    public ResponseEntity<List<RendezVous>> getDemandes(@PathVariable Long id) {
        return ResponseEntity.ok(rendezVousService.getDemandesPourMedecin(id));
    }
}
