package md.voil.api.Domain.DTO.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressRegister {
        @NotBlank
        private String street;
        @NotBlank
        private String neighborhood;
        @NotBlank
        @Pattern(regexp = "(^\\d{8}$)|(^\\d{5}-\\d{3}$)|(^\\d{2}.\\d{3}-\\d{3}$)")
        private String zipcode;
        @NotBlank
        private String city;
        @NotBlank
        private String state;
        private String number;
        private String complement;
}