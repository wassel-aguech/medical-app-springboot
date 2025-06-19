package pfe.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfe.dto.DisponibiliteDto;
import pfe.dto.MedecinDto;
import pfe.repository.DisponibiliteRepository;
import pfe.services.DisponibiliteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/disponibilites")
@RequiredArgsConstructor
public class DisponibiliteController {

    private final DisponibiliteService disponibiliteService;



    @PostMapping("/addDisponibilite")
    public DisponibiliteDto addDisponibilite(@RequestBody DisponibiliteDto disponibiliteDto  ) {
        return disponibiliteService.addDisponibilite(disponibiliteDto);
    }

    @GetMapping("/getalldesponibilte")
    public ResponseEntity<List<DisponibiliteDto>> getAllDisponibilites() {
        return ResponseEntity.ok(disponibiliteService.getAllDisponibilite());
    }


    @GetMapping("/getDisponibiliteById/{id}")
    public ResponseEntity<DisponibiliteDto> getdisponibiliteById(@PathVariable Long id) {
        Optional<DisponibiliteDto> dsponibilite = disponibiliteService.getDisponibiliteById(id);
        return dsponibilite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }





    @DeleteMapping("/deleteDisponibilite/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable Long id) {
        disponibiliteService.deleteDisponibilite(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/updateDisponibilite")
    public ResponseEntity<DisponibiliteDto> updateDisponibilite( @RequestBody DisponibiliteDto disponibiliteDto) {
        return ResponseEntity.ok(disponibiliteService.updateDisponibilite(disponibiliteDto));
    }


    @GetMapping("/medecin/{id}")
    public List<DisponibiliteDto> getDisponibilitesByMedecin(@PathVariable("id") Long id) {
        return disponibiliteService.getDisponibilitesByMedecinId(id);
    }
}
