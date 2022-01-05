package com.example.amtech.security;

import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.LoginService;
import com.example.amtech.services.ShoppingCartService;
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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    HttpSession httpSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> authorities = new LinkedList<>();

        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        JSONObject result = loginService.postRequest(body);
        if(!result.isNull("error")){
            throw new BadCredentialsException(result.getString("error"));
        }
        String token = result.getString("token");
        System.out.println("body: " + token);
        JSONObject account = result.getJSONObject("account");

        //Set Session
        httpSession.setAttribute("user_id", account.getInt("id"));
        httpSession.setAttribute("username", account.getString("username"));
        httpSession.setAttribute("role", account.getString("role"));
        httpSession.setAttribute("jwt_token", token);

        //Set Authentification
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getString("role").toUpperCase()));
        return new UsernamePasswordAuthenticationToken(account.getString("username"), null, authorities);
    }

    // Define if this AuthenticationProvider supports the indicated AuthenticationMethod object
    @Override
    public boolean supports(Class<?> authenticationMethod) {
        return authenticationMethod.equals(UsernamePasswordAuthenticationToken.class);
    }
}
