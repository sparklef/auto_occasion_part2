package com.c_project.auto_occasion.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.c_project.auto_occasion.model.Utilisateur_site;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private String secret="This is secret";

    public String generateJwt(Utilisateur_site user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String sss=dtf.format(now);
        System.out.println("C Date "+dtf.format(now));
//		Claims claims = Jwts.claims()
//				.setIssuer(user.getId().toString())
//				.setIssuedAt(issuedAt)
//				.setExiration(expiryAt);
//		claims
//		setIssuer - means who is issued to this token
//		Claims claims = Jwts.claims()
//				.setIssuer(user.getId().toString())
//				.setIssuedAt(issuedAt)
//				.setExpiration(issuedAt);
//		generate jwt using claims

        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("last login", sss);
        claims.put("id", user.getIdUser());
        claims.put("email", user.getEmail());
        claims.put("name", user.getNom());
        String ss=Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println("SS is "+ss);
        return ss;
    }
}
