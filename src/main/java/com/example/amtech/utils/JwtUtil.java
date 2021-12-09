package com.example.amtech.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.binary.StringUtils;
import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.binary.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.SignatureException;
import java.util.function.Function;

@Slf4j
@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;


    public Boolean validateJwtToken(String token) {
        try {
            //Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            Algorithm algorithm = Algorithm.HMAC256("czvFbg2kmvqbcu(7Ux+c");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("IICT").build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("token jwt validation ok jjwt");
            return true;
        } catch (JWTVerificationException exception){
            System.out.println("Invalid signature/claims");
            //Invalid signature/claims
            return false;
        }
    }

    public Boolean verify(String authorization){
        try {
            //Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authorization).getBody();
            Claims claims = Jwts.parser().setSigningKey("czvFbg2kmvqbcu(7Ux+c").parseClaimsJws(authorization).getBody();
            System.out.println("token jwt validation ok java-awt");
            return true;
        } catch(Exception e) {
            System.out.println("Access Denied");
            System.out.println(e.getMessage());
            return false;
        }

    }

    public String decode(final String base64) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }
/*
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        //return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody();
        //return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(("czvFbg2kmvqbcu(7Ux+c").getBytes())).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateJwtToken(String token) {
        return !isTokenExpired(token);
    }
    */

}