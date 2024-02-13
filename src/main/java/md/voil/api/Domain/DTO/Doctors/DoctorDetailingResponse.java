package md.voil.api.Domain.DTO.Doctors;

import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.Entity.Address.Address;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;

@Getter
@Setter
public class DoctorDetailingResponse {

    private int id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    private Specialty specialty;
    private Address address;

    public DoctorDetailingResponse(Doctor doctor){
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.crm = doctor.getCrm();
        this.telephone = doctor.getTelephone();
        this.specialty = doctor.getSpecialty();
        this.address = doctor.getAddress();
    }
}
