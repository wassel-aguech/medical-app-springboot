package pfe.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.services.MedecinService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medecin")
@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;



    @PostMapping("addPatient")
    public MedecinDto addMedecin(@RequestBody MedecinDto medecinDto) {
        return medecinService.addMedecin(medecinDto);
    }


    @GetMapping("/getallmedecins")
    public ResponseEntity<List<MedecinDto>> getAllMedecin() {
        return ResponseEntity.ok(medecinService.getAllMedecin());
    }


    @GetMapping("/getMedecinById/{id}")
    public ResponseEntity<MedecinDto> getMedecinById(@PathVariable Long id) {
        Optional<MedecinDto> medecin = medecinService.getMedecinById(id);
        return medecin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update")
    public ResponseEntity<MedecinDto> updateMedecin(@RequestBody MedecinDto medecinDto) {
        return ResponseEntity.ok(medecinService.updateMedecin(medecinDto));
    }




}
