package md.voil.api.Repository.Generic;

import md.voil.api.Domain.DTO.Address.AddressRegister;
import md.voil.api.Domain.DTO.Doctors.DoctorRegister;
import md.voil.api.Domain.DTO.Patient.PatientRegister;
import md.voil.api.Domain.Entity.Appointment.Appointment;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;
import md.voil.api.Domain.Entity.Patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public class GenericPersist implements IGenericPersist {

    private TestEntityManager entityManager;

    public GenericPersist(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Appointment persistAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        Appointment appointment = new Appointment(0, doctor, patient, date);
        entityManager.persist(appointment);

        return appointment;
    }

    @Override
    public Doctor persistDoctor(String name, String email, String crm, String telephone, Specialty specialty) {
        Doctor doctor = new Doctor(new DoctorRegister(name, email, crm, telephone, specialty, addressRegister()));
        entityManager.persist(doctor);

        return doctor;
    }

    @Override
    public Patient persistPatient(String name, String email, String telephone, String cpf) {
        Patient patient = new Patient(new PatientRegister(name, email, cpf, telephone, addressRegister()));
        entityManager.persist(patient);

        return  patient;
    }
    private AddressRegister addressRegister() {
        return new AddressRegister(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
