package pfe.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrément
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateEnvoi;
    private String motif;
    private Date dateDemande;
    private Date dateRendezVous;
    private statusRendezVous statut;

    @ManyToOne()
    @JoinColumn(name = "patient_id" , insertable = false, updatable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "medecin_id" , insertable = false, updatable = false)
    private Medecin medecin;



}
