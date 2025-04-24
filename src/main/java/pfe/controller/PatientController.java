package pfe.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pfe.configImage.ImageStorage;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.dto.RendezVousDto;
import pfe.services.PatientService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final ImageStorage imageStorage;


    @PostMapping("addPatient")
    public PatientDto addPatient(@RequestBody PatientDto patientDto) {
        return patientService.addPatient(patientDto);
    }

    @GetMapping("getall")
    public List<PatientDto> getAllPatient() {
        return patientService.getAllPatient();
    }
   @GetMapping("getPatientById/{id}")
    public Optional<PatientDto> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("deletePatient/{id}")
    public void deletePatient( @PathVariable Long id) {
        patientService.deletePatient(id);
    }


    @PutMapping("updatePatient")
    public PatientDto updatePatient(@RequestBody PatientDto patientdto) {
        return patientService.updatePatient(patientdto);
    }




    @PostMapping(path = "/uploadImage/{IdPatient}" , consumes = MULTIPART_FORM_DATA_VALUE)
    public PatientDto uploadMedecinImage(@PathVariable("IdPatient")  Long IdPatient,
                                         @RequestPart(value = "image") MultipartFile image) {
        return patientService.uploadPaientImage(IdPatient, image);
    }

    @GetMapping("/downloadblogimage/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }














}
