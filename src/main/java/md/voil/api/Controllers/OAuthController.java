package md.voil.api.Controllers;

import jakarta.validation.Valid;
import md.voil.api.Service.DTOs.OAuth.Interfaces.IOAuthService;
import md.voil.api.Service.DTOs.OAuth.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
