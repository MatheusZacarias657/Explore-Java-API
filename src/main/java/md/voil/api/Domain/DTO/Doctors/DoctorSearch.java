package md.voil.api.Domain.DTO.Doctors;

import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.Entity.Doctor.Doctor;
import md.voil.api.Domain.Entity.Doctor.Specialty;

@Getter
@Setter
public class DoctorSearch {
    private int id;
    private String name;
    private String email;
    private String crm;
    private Specialty specialty;

    public DoctorSearch(Doctor doctor){
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.crm = doctor.getCrm();
        this.specialty = doctor.getSpecialty();
    }
}
