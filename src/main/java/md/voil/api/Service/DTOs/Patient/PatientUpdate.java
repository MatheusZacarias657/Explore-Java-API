package md.voil.api.Service.DTOs.Patient;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Service.DTOs.Address.AddressRegister;

@Getter
@Setter
public class PatientUpdate {
    @NotNull
    private int id;
    private String name;
    private String telephone;
    private AddressRegister address;
}
