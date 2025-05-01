package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfe.entities.RendezVous;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByMedecinId(Long medecinId);



    @Query("SELECT r FROM RendezVous r WHERE r.patient.id = :patientId")
    List<RendezVous> findRendezVousByPatientId(@Param("patientId") Long patientId);


    @Query("SELECT a FROM RendezVous a WHERE a.medecin.id = :medecinId")
    List<RendezVous> findRendezVousByMedecinId(@Param("medecinId") Long medecinId);


}
