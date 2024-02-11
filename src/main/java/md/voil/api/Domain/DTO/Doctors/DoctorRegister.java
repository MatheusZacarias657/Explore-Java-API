package md.voil.api.Domain.DTO.Doctors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import md.voil.api.Domain.DTO.Address.AddressRegister;
import md.voil.api.Domain.Entity.Doctor.Specialty;

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
