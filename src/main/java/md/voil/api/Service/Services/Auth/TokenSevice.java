package md.voil.api.Service.Services.Auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import md.voil.api.Repository.Domain.User.User;
import md.voil.api.Service.DTOs.OAuth.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TokenSevice {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration}")
    private String expirationTime;

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
        TemporalAmount expiration = convertStringToTemporalAmount(expirationTime);
        return LocalDateTime.now().plus(expiration).toInstant(zoneOffset);
    }

    private TemporalAmount convertStringToTemporalAmount(String timeString) {
        Pattern pattern = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");
        Matcher matcher = pattern.matcher(timeString);

        if (matcher.matches()) {
            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            int seconds = Integer.parseInt(matcher.group(3));

            return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        } else {
            throw new IllegalArgumentException("Invalid time format: " + timeString);
        }
    }
}
