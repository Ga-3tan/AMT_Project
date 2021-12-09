package com.example.amtech.security;

import com.example.amtech.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /*
    public WebSecurityConfig() {
        super(true); // disable default
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("amtech").password("Xkxtxyglu@z9Afacb-k1").authorities("USER", "ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /* *  matches zero or more characters
           ** matches zero or more directories in a path*/
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin/*").authenticated();//.hasAuthority("ADMIN");
                //.and().securityContext() // store the user after logging in
                //.and().exceptionHandling()// exception translation for security-related exceptions
                //.and().servletApi();// enable the Servlet API integration so that you can use the methods on HttpServletRequest to do checks in your view
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}