package com.example.amtech.filters;

import com.example.amtech.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if( SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) { // If isAuthenticated

            System.out.println("I have a session");
            HttpSession session = request.getSession(false);
            System.out.println(session);
            String jwt_token = (String) session.getAttribute("jwt_token");
            System.out.println(jwt_token);
            if (jwt_token != null) {
                if (!jwtUtil.validateJwtToken(jwt_token) || jwtUtil.isTokenExpired(jwt_token)) { // Check JWT
                    System.out.println("INVALID TOKEN");
                    // Logout
                    session.invalidate();
                    SecurityContextHolder.clearContext();
                }
                System.out.println("VALID TOKEN");
            }
        }

        chain.doFilter(request, response);
    }

}