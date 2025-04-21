package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
