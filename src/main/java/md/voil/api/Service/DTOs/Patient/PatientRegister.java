package md.voil.api.Service.DTOs.Patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Repository.Domain.Doctor.Specialty;
import md.voil.api.Service.DTOs.Address.AddressRegister;

@Getter
@Setter
public class PatientRegister {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telephone;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    private String cpf;

    @NotNull
    @Valid
    private AddressRegister address;
}
