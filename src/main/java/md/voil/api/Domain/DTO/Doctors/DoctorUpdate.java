package md.voil.api.Domain.DTO.Doctors;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.DTO.Address.AddressRegister;

@Getter
@Setter
public class DoctorUpdate {
    @NotNull
    private int id;
    private String name;
    private String telephone;
    private AddressRegister address;
}
