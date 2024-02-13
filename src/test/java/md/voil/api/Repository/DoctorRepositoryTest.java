package md.voil.api.Repository;

import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;
import md.voil.api.Domain.Entity.Patient.Patient;
import md.voil.api.Repository.Generic.GenericPersist;
import md.voil.api.Repository.Generic.IGenericPersist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    private DoctorRepository doctorRepository;

    private IGenericPersist genericPersist;

    @Autowired
    public DoctorRepositoryTest(DoctorRepository doctorRepository, TestEntityManager entityManager) {
        this.doctorRepository = doctorRepository;
        this.genericPersist = new GenericPersist(entityManager);
    }

    @Test
    @DisplayName("When Doctor not available in the date")
    void chooseAvailableRandomDoctorTestOne() {
        LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        Doctor doctor = genericPersist.persistDoctor("Doctor", "test@test.com", "123456", "123456789", Specialty.cardiologia);
        Patient patient = genericPersist.persistPatient("Patient", "test2@test.com", "123456789", "12345678901");
        genericPersist.persistAppointment(doctor, patient, nextMonday);

        Doctor doctorTest = doctorRepository.chooseAvailableRandomDoctor(Specialty.cardiologia, nextMonday);

        assertThat(doctorTest).isNull();
    }

    @Test
    @DisplayName("When Doctor is available in the date")
    void chooseAvailableRandomDoctorTestTwo() {
        LocalDateTime nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        Doctor doctor = genericPersist.persistDoctor("Doctor", "test@test.com", "123456", "123456789", Specialty.cardiologia);

        Doctor doctorTest = doctorRepository.chooseAvailableRandomDoctor(Specialty.cardiologia, nextMonday);

        assertThat(doctorTest).isEqualTo(doctor);
    }
}