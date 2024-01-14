package md.voil.api.Repositories;

import md.voil.api.Domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
}
