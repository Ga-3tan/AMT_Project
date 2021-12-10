package com.example.amtech.filters;
/*
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.amtech.utils.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Optional<Cookie> jwtCookie = null;

        //Find cookie
        if (request.getCookies() != null) {
            jwtCookie = Arrays.stream(request.getCookies()).filter(cookie ->
                    cookie.getName().equals("token")).findFirst();
        }

        //Extract jwt from cookie
        if(jwtCookie != null && jwtCookie.isPresent()) {
            System.out.println("JWTCookie: " + jwtCookie.get().getValue());
        }else{
            System.out.println("No JWTCookie ");
        }

        //Extraction data from jwt Token
        String payload  = null;
        String username = null;
        DecodedJWT jwt  = null;
        if (jwtCookie != null && jwtCookie.isPresent()) {
            try {
                 jwt = JWT.decode(jwtCookie.get().getValue());
            } catch (JWTDecodeException exception){
                //Invalid token
            }
            //username = jwt.getSubject(); //Trigger /login ??
            payload = jwtUtil.decode(jwt.getPayload());
            System.out.println("Payload: " + payload);
            username = new JSONObject(payload).getString("sub");
            System.out.println("Username: " + username);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }

}*/