package md.voil.api.Application.Service.Auth;

import md.voil.api.Domain.Entity.User.User;
import md.voil.api.Domain.Interface.OAuth.IOAuthService;
import md.voil.api.Domain.DTO.OAuth.TokenResponse;
import md.voil.api.Domain.DTO.OAuth.UserLogin;
import md.voil.api.Domain.Interface.OAuth.ITokenSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OAuthService implements IOAuthService {

    private AuthenticationManager manager;
    private ITokenSevice tokenSevice;

    @Autowired
    public OAuthService(AuthenticationManager manager, ITokenSevice tokenSevice){
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
