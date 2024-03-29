package md.voil.api.Domain.DTO.Patient;

import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.Entity.Address.Address;
import md.voil.api.Domain.Entity.Patient.Patient;

@Getter
@Setter
public class PatientDetailingResponse {

    private int id;
    private String name;
    private String email;
    private String telephone;
    private String cpf;
    private Address address;

    public PatientDetailingResponse(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.telephone = patient.getTelephone();
        this.cpf = patient.getCpf();
        this.address = patient.getAddress();
    }
}
