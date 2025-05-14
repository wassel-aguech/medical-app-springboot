package pfe.services.impl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pfe.dto.MedecinDto;
import pfe.dto.RendezVousDto;
import pfe.entities.*;
import pfe.repository.MedecinRepository;
import pfe.repository.PatientRepository;
import pfe.repository.RendezVousRepository;
import pfe.services.RendezVousService;
import pfe.websocket.NotificationService;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousServiceImpl  implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final MedecinRepository medecinRepository;
    private final NotificationService notificationService;


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


    @Override
    @Transactional
    public RendezVousDto DemandeRendezVous(Authentication connectedUser, RendezVousDto rendezVousDto) {
        Patient patient = ((Patient) connectedUser.getPrincipal());

        RendezVous rendezVous = RendezVousDto.toEntity(rendezVousDto);
        rendezVous.setPatient(patient);

        Medecin medecin = medecinRepository.findById(rendezVousDto.getMedecinid())
                .orElseThrow(() -> new EntityNotFoundException("Médecin introuvable"));

        rendezVous.setMedecin(medecin);
        rendezVous.setDateEnvoi(new Date());
        rendezVous.setStatut(statusRendezVous.EN_ATTENTE);

        rendezVous = rendezVousRepository.save(rendezVous);

        String message = "nouvelle demande de rendez-vous du patient : " + patient.getFirstName() + " " + patient.getLastName();
        notificationService.sendNotification(medecin.getId(), message ,NotificationType.RENDEZVOUS);

        return RendezVousDto.toDto(rendezVous);
    }


    /*
        public RendezVous validerRendezVous(Long rdvId, Date dateFinale) {
            RendezVous rdv = rendezVousRepository.findById(rdvId).orElseThrow();
            rdv.setDateRendezVous(dateFinale);
            rdv.setStatut(statusRendezVous.VALIDE); // ici
            return rendezVousRepository.save(rdv);
        }
    */
    public List<RendezVous> getDemandesPourMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId);
    }


    @Override
    public List<RendezVousDto> getRendezVousByPatientId(Long patientId) {
        return rendezVousRepository.findRendezVousByPatientId(patientId)
                .stream()
                .map(RendezVousDto::toDto)
                .collect(Collectors.toList());


    }

    @Override
    public List<RendezVousDto> getRendezVousByMedecinId(Long medecinId) {
        return rendezVousRepository.findRendezVousByMedecinId(medecinId)
                .stream()
                .map(RendezVousDto::toDto)
                .collect(Collectors.toList());


    }

    @Override
    public List<RendezVousDto> getRendezVousValidesByPatient(Long patientId) {
        return rendezVousRepository.findRendezVousValidesByPatientId(patientId)
                .stream()
                .map(RendezVousDto::toDto)
                .collect(Collectors.toList());
    }




    @Override
    public List<RendezVousDto> getRendezVousTodayByMedecin(Long medecinId) {
        // Calcul début et fin de journée
        LocalDate today = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();

        Date startOfDay = Date.from(today.atStartOfDay(zone).toInstant());
        Date endOfDay = Date.from(today.plusDays(1).atStartOfDay(zone).toInstant());

        // Requête dans l'intervalle
        List<RendezVous> rendezVousList = rendezVousRepository
                .findByMedecinIdAndDateEnvoiBetween(medecinId, startOfDay, endOfDay);

        // Conversion en DTO
        return rendezVousList.stream()
                .map(RendezVousDto::toDto)
                .collect(Collectors.toList());
    }









}