package md.voil.api.Domain.Entity.Doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import md.voil.api.Domain.Entity.Address.Address;
import md.voil.api.Domain.DTO.Doctors.DoctorRegister;
import md.voil.api.Domain.DTO.Doctors.DoctorUpdate;

@Table(name = "Doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String crm;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorRegister doctor) {

        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.crm = doctor.getCrm();
        this.telephone = doctor.getTelephone();
        this.specialty = doctor.getSpecialty();
        this.address = new Address(doctor.getAddress());
        this.active = true;
    }

    public void Update(DoctorUpdate doctor) {
        this.name = (doctor.getName() != null) ? doctor.getName() : this.name;
        this.telephone = (doctor.getTelephone() != null) ? doctor.getTelephone() : this.telephone;
        this.address = (doctor.getAddress() != null) ? new Address(doctor.getAddress()) : this.address;
    }

    public void disableRegister() {
        this.active = false;
    }
}
