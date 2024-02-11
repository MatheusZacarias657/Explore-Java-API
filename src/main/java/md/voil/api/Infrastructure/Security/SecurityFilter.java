package md.voil.api.Infrastructure.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import md.voil.api.Domain.Interface.OAuth.ITokenSevice;
import md.voil.api.Repository.UserRepository;
import md.voil.api.Application.Service.Auth.TokenSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private ITokenSevice tokenSevice;
    private UserRepository repository;

    @Autowired
    public SecurityFilter(ITokenSevice tokenSevice, UserRepository repository) {
        this.tokenSevice = tokenSevice;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if (token != null){
            String subject = tokenSevice.getSubject(token);
            UserDetails user = repository.findByLogin(subject);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null)
            return authHeader.replace("Bearer ", "");

        return null;
    }
}
