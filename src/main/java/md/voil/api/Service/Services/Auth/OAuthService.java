package md.voil.api.Service.Services.Auth;

import md.voil.api.Repository.Domain.User.User;
import md.voil.api.Service.DTOs.OAuth.Interfaces.IOAuthService;
import md.voil.api.Service.DTOs.OAuth.TokenResponse;
import md.voil.api.Service.DTOs.OAuth.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OAuthService implements IOAuthService {

    private AuthenticationManager manager;
    private TokenSevice tokenSevice;

    @Autowired
    public OAuthService(AuthenticationManager manager, TokenSevice tokenSevice){
        this.manager = manager;
        this.tokenSevice = tokenSevice;
    }

    public TokenResponse Login(UserLogin login){

        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
            Authentication auth = manager.authenticate(authenticationToken);
            String token = tokenSevice.generateToken((User) auth.getPrincipal());

            return new TokenResponse(token);
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
