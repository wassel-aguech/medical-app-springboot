package pfe.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long destinataireId;
    private String message;
    private Date dateEnvoi;
    private boolean lue = false;


    @Enumerated(EnumType.STRING)
    private NotificationType type;
}
