package pfe.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ReponseRendezVousDto {

    private Date dateRendezVous;
    private String motif;
}
