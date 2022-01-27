package com.example.amtech.security;

import com.example.amtech.services.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * This CustomAuthenticationProvider is used to handle authentications and
 * provides a POST endpoint to the "/login" route.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;
    @Autowired
    private HttpSession httpSession;

    /**
     * This method is called when the client makes an authentication attempt at the "/login" route.
     * It will get the username and password from the Authentication object and will send a login request
     * to the authentication microservice, if the authentication is successful it will create
     * an authentication session for the user.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> authorities = new LinkedList<>();

        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        // Send username and password to authentication microservice
        JSONObject result = loginService.signInUser(body);
        if(!result.isNull("error")){
            throw new BadCredentialsException(result.getString("error"));
        }
        String token = result.getString("token");
        JSONObject account = result.getJSONObject("account");

        // Set Session
        httpSession.setAttribute("user_id", account.getInt("id"));
        httpSession.setAttribute("username", account.getString("username"));
        httpSession.setAttribute("role", account.getString("role"));
        httpSession.setAttribute("jwt_token", token);

        // Set Authentication
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getString("role").toUpperCase()));
        return new UsernamePasswordAuthenticationToken(account.getString("username"), null, authorities);
    }

    /**
     * Define if this AuthenticationProvider supports the indicated AuthenticationMethod object
     */
    @Override
    public boolean supports(Class<?> authenticationMethod) {
        return authenticationMethod.equals(UsernamePasswordAuthenticationToken.class);
    }
}
