package pfe.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.util.Date;
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
    @JsonFormat(pattern = "HH:mm")
    private Date heureDebut;
    @JsonFormat(pattern = "HH:mm")
    private Date heureFin;


    @ManyToOne
    private Medecin medecin;

}
