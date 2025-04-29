package pfe.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pfe.configImage.ImageStorage;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.services.MedecinService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/medecins")
@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;
    private final ImageStorage imageStorage;


    @PostMapping("addMedecin")
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

    @DeleteMapping("/deletemedecin/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update")
    public ResponseEntity<MedecinDto> updateMedecin(@RequestBody MedecinDto medecinDto) {
        return ResponseEntity.ok(medecinService.updateMedecin(medecinDto));
    }



    @PostMapping(path = "/uploadImage/{IdMedecin}" , consumes = MULTIPART_FORM_DATA_VALUE)
    public MedecinDto uploadMedecinImage(@PathVariable("IdMedecin")  Long IdMedecin,
                                   @RequestPart(value = "image") MultipartFile image) {
        return medecinService.uploadMedecinImage(IdMedecin, image);
    }

    @GetMapping("/downloadblogimage/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }














}
