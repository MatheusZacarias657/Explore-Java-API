package md.voil.api.Repository;

import md.voil.api.Domain.Entity.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
            FROM Appointment a
            where a.patient.id = :idPatient
            and
            CAST(a.date AS DATE) = :date
            """)
    boolean existsByPatientIdAndDateBetween(int idPatient, LocalDate date);

    @Query("""
            SELECT CASE WHEN EXISTS (
                SELECT 1
                FROM Appointment a
                WHERE a.doctor.id = :idDoctor
                AND a.date = :date
            ) THEN true ELSE false END
        """)
    boolean existsByDoctorIdAndDate(int idDoctor, LocalDateTime date);
}
