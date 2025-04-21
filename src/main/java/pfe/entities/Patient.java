package pfe.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Patient extends User{

    private boolean ispatient;
    private String sexe;


    @ManyToOne()
    private Medecin medecin;


    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVous;

}
