package pfe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.RendezVous;
import pfe.entities.statusRendezVous;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RendezVousDto {


   private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateEnvoi;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)

    private Date dateRendezVous;
    private statusRendezVous statut;
    private String motif;


    private Long patientid;
    private Long medecinid;
    private String medecinName;


     public  static RendezVous toEntity(RendezVousDto rendezVousDto){
         return  RendezVous.builder()
                 .id(rendezVousDto.getId())
                 .motif(rendezVousDto.getMotif())
                 .dateEnvoi(rendezVousDto.getDateEnvoi())
                 .dateDemande(rendezVousDto.getDateDemande())
                 .dateRendezVous(rendezVousDto.getDateRendezVous())
                 .statut(rendezVousDto.getStatut())
                 .build();
     }


    public  static RendezVousDto  toDto(RendezVous rendezVous){
        return  RendezVousDto.builder()
                .id(rendezVous.getId())
                .motif(rendezVous.getMotif())
                .dateEnvoi(rendezVous.getDateEnvoi())
                .dateDemande(rendezVous.getDateDemande())
                .dateRendezVous(rendezVous.getDateRendezVous())
                .statut(rendezVous.getStatut())

                .patientid(rendezVous.getPatient().getId())
                .medecinid(rendezVous.getMedecin().getId())
                .medecinName(rendezVous.getMedecin().getFirstName())

                .build();
    }




}
