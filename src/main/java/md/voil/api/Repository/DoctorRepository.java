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
            select d from Doctor d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select d.id from Appointment a
                where
                a.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor chooseAvailableRandomDoctor(Specialty specialty, LocalDateTime date);
}
