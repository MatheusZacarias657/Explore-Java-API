package md.voil.api.Domain.Interface.OAuth;

import md.voil.api.Domain.Entity.User.User;

public interface ITokenSevice {
    String generateToken(User user);

    String getSubject(String token);
}
