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


   private int id;

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


     public  static RendezVous toEntity(RendezVousDto rendezVousDTO){
         return  RendezVous.builder()
                 .motif(rendezVousDTO.getMotif())
                 .build();
     }


    public  static RendezVousDto  toDto(RendezVous rendezVous){
        return  RendezVousDto.builder()
                .motif(rendezVous.getMotif())
                .patientid(rendezVous.getPatient().getId())
                .build();
    }




}
