package pfe.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Disponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jour;
    private String heureDebur;
    private String heureFin;


    @ManyToMany
    private List<Medecin> medecins;

}
