package com.Charmeetchic.Usuario.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.Charmeetchic.Usuario.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET = "charme-2025-key";

    // 6 MESES = 180 días
    private static final long EXPIRATION_MS = 1000L * 60 * 60 * 24 * 180;

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo())
                .claim("rol", usuario.getRol().name())
                .claim("id", usuario.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getCorreo(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRol(String token) {
        return (String) Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("rol");
    }
}
