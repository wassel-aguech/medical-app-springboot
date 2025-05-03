package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfe.entities.Disponibilite;

import java.util.List;

public interface DisponibiliteRepository extends JpaRepository <Disponibilite, Long> {



    @Query("SELECT d FROM Disponibilite d WHERE d.medecin.id = :medecinId")
    List<Disponibilite> findByMedecinId(@Param("medecinId") Long medecinId);
}
