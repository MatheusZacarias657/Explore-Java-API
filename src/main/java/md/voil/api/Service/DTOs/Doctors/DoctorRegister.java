package md.voil.api.Service.DTOs.Doctors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Service.DTOs.Address.AddressRegister;
import md.voil.api.Repository.Domain.Doctor.Specialty;

@Getter
@Setter
public class DoctorRegister {
        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String telephone;

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        private String crm;
        @NotNull
        private Specialty specialty;
        @NotNull
        @Valid
        private AddressRegister address;
}
