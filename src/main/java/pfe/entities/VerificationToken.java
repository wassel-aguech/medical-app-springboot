package pfe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registration_token")
public class VerificationToken {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String token;
   private Date expirationTime;


   private static final int EXPIRATION_TIME = 1;


   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;


   @CreationTimestamp
   @Column(name = "created_at")
   private LocalDateTime createdAt;




   public VerificationToken(String token, User user) {
       super();
       this.token = token;
       this.user = user;
       this.expirationTime = this.getTokenExpirationTime();
   }


   public Date getTokenExpirationTime() {
       Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(new Date().getTime());
       calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
       return new Date(calendar.getTime().getTime());
   }


}
