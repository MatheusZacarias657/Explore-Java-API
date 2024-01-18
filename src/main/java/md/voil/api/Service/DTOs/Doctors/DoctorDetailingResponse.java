package md.voil.api.Service.DTOs.Doctors;

import lombok.Getter;
import lombok.Setter;
import md.voil.api.Repository.Domain.Doctor.Address;
import md.voil.api.Repository.Domain.Doctor.Doctor;
import md.voil.api.Repository.Domain.Doctor.Specialty;

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
