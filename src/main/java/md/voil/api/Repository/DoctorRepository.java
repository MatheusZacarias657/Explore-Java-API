package md.voil.api.Repository;

import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select d.active
            from Doctor d
            where
            d.id = :id
            """)
    Boolean findActiveById(int id);

    @Query("""
           SELECT d
           FROM Doctor d
           WHERE d.specialty = :specialty
           AND NOT EXISTS (
               SELECT 1
               FROM  Appointment a
               WHERE a.doctor.id = d.id
               AND a.date = :date
           )
           """)
    Doctor chooseAvailableRandomDoctor(Specialty specialty, LocalDateTime date);
}
