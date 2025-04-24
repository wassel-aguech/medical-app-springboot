package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.entities.RendezVous;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByMedecinId(Long medecinId);

}
