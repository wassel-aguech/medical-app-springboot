package pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeRendezVousDto {

    private Long patientId;
    private Long medecinId;
    private String motif;
    private Date dateDemande;
}
