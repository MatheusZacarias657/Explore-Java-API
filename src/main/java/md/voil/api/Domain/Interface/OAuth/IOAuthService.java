package md.voil.api.Domain.Interface.OAuth;

import md.voil.api.Domain.DTO.OAuth.TokenResponse;
import md.voil.api.Domain.DTO.OAuth.UserLogin;

public interface IOAuthService {
    TokenResponse Login(UserLogin login);
}
