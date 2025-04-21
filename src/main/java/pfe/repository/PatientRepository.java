package pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
