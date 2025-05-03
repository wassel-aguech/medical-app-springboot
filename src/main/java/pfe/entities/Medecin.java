package pfe.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
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
public class Medecin extends User{

    private String specialite;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Etat etat;

    private String image;

    @OneToMany(mappedBy = "medecin")
    private List<Patient> patients;

    @OneToMany
    private List<Disponibilite> disponibilites;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVous;

    @OneToMany(mappedBy = "medecin")
    private List<Notification> notifications;
}
