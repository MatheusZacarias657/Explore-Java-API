package md.voil.api.Controllers;

import jakarta.validation.Valid;
import md.voil.api.Domain.Interface.OAuth.IOAuthService;
import md.voil.api.Domain.DTO.OAuth.UserLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OAuth")
public class OAuthController {

    private final IOAuthService authService;

    public OAuthController(IOAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/Login")
    public ResponseEntity Login(@RequestBody @Valid UserLogin login){

        return ResponseEntity.ok(authService.Login(login));
    }
}
