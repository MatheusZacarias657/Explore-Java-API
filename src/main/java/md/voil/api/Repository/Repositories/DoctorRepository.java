package md.voil.api.Repository.Repositories;

import md.voil.api.Repository.Domain.Doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
}
