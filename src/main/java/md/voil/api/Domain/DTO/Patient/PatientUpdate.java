package md.voil.api.Domain.DTO.Patient;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.DTO.Address.AddressRegister;

@Getter
@Setter
public class PatientUpdate {
    @NotNull
    private int id;
    private String name;
    private String telephone;
    private AddressRegister address;
}
