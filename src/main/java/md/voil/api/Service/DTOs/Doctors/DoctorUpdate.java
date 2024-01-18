package md.voil.api.Service.DTOs.Doctors;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Service.DTOs.Address.AddressRegister;

@Getter
@Setter
public class DoctorUpdate {
    @NotNull
    private int id;
    private String name;
    private String telephone;
    private AddressRegister address;
}
