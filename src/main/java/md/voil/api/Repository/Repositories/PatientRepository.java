package md.voil.api.Repository.Repositories;

import md.voil.api.Repository.Domain.Patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Page<Patient> findAllByActiveTrue(Pageable pageable);
}
