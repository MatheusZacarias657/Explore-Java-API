package md.voil.api.Service.DTOs.Patient;

import lombok.Getter;
import lombok.Setter;
import md.voil.api.Repository.Domain.Patient.Patient;

@Getter
@Setter
public class PatientSearch {

    private int id;
    private String name;
    private String email;
    private String cpf;

    public PatientSearch(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.cpf = patient.getCpf();
    }
}
