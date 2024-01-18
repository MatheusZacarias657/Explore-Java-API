package md.voil.api.Service.DTOs.OAuth.Interfaces;

import md.voil.api.Service.DTOs.OAuth.TokenResponse;
import md.voil.api.Service.DTOs.OAuth.UserLogin;

public interface IOAuthService {
    TokenResponse Login(UserLogin login);
}
