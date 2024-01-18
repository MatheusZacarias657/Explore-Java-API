package md.voil.api.Service.DTOs.OAuth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
