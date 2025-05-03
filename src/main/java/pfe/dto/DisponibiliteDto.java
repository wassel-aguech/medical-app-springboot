package pfe.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.Disponibilite;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DisponibiliteDto {

    private Long id;
    private String jour;
    @JsonFormat(pattern = "HH:mm")
    private Date heureDebut;
    @JsonFormat(pattern = "HH:mm")
    private Date heureFin;

    private Long medecinId;


    public static Disponibilite toEntity (DisponibiliteDto disponibiliteDto){
        return Disponibilite.builder()
                .id(disponibiliteDto.getId())
                .jour(disponibiliteDto.getJour())
                .heureDebut(disponibiliteDto.getHeureDebut())
                .heureFin(disponibiliteDto.getHeureFin())
                .build();
    }

    public static DisponibiliteDto toDto (Disponibilite disponibilite){
        return DisponibiliteDto.builder()
                .id(disponibilite.getId())
                .jour(disponibilite.getJour())
                .heureDebut(disponibilite.getHeureDebut())
                .heureFin(disponibilite.getHeureFin())
                .medecinId(disponibilite.getMedecin().getId())
                .build();
    }
}
