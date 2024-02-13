package md.voil.api.Application.Service.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import md.voil.api.Application.Utils.*;
import md.voil.api.Domain.Entity.User.User;
import md.voil.api.Domain.Interface.OAuth.ITokenSevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAmount;
@Service
public class TokenSevice implements ITokenSevice {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration}")
    private String expirationTime;

    @Override
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw ex;
        }
    }

    @Override
    public String getSubject(String token){

        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return  JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex){
            throw ex;
        }
    }

    private Instant expirationDate() {

        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(java.time.Instant.now());
        TemporalAmount expiration = TimeTools.convertStringToTemporalAmount(expirationTime);
        return LocalDateTime.now().plus(expiration).toInstant(zoneOffset);
    }


}
