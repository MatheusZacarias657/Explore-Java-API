package md.voil.api.Repository.Generic;

import md.voil.api.Domain.Entity.Appointment.Appointment;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;
import md.voil.api.Domain.Entity.Patient.Patient;

import java.time.LocalDateTime;

public interface IGenericPersist {
    Appointment persistAppointment(Doctor doctor, Patient patient, LocalDateTime date);

    Doctor persistDoctor(String name, String email, String crm, String telephone, Specialty specialty);

    Patient persistPatient(String name, String email, String telephone, String cpf);
}
