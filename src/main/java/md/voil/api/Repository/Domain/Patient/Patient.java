package md.voil.api.Repository.Domain.Patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import md.voil.api.Repository.Domain.Address.Address;
import md.voil.api.Service.DTOs.Patient.PatientRegister;
import md.voil.api.Service.DTOs.Patient.PatientUpdate;

@Table(name = "patients")
@Entity(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientRegister patientRegister){
        this.name = patientRegister.getName();
        this.email = patientRegister.getEmail();
        this.telephone = patientRegister.getTelephone();
        this.cpf = patientRegister.getCpf();
        this.address = new Address(patientRegister.getAddress());
    }

    public void Update(PatientUpdate patient) {
        this.name = (patient.getName() != null) ? patient.getName() : this.name;
        this.telephone = (patient.getTelephone() != null) ? patient.getTelephone() : this.telephone;
        this.address = (patient.getAddress() != null) ? new Address(patient.getAddress()) : this.address;
    }

    public void disableRegister() {
        this.active = false;
    }
}
