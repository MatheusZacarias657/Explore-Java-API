package md.voil.api.Domain.Entity.Address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import md.voil.api.Domain.DTO.Address.AddressRegister;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressRegister address) {

        this.street = address.getStreet();
        this.neighborhood = address.getNeighborhood();
        this.zipcode = address.getZipcode();
        this.city = address.getCity();
        this.state = address.getState();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }
}
