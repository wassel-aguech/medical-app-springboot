package pfe.entities;


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
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long senderId;
    private Long receiverId;
    private String senderName;
    private String content;
    private String timestamp;


    @ManyToOne
    private Conversation conversation;




}
