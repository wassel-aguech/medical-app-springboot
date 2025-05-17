package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfe.entities.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {



    @Query("SELECT n FROM Notification n WHERE n.destinataireId = :medecinId ORDER BY n.dateEnvoi DESC")
    List<Notification> findAllByDestinataireId(@Param("medecinId") Long medecinId);
}
