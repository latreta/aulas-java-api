package com.poli.policlass.config.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${policlass.jwt.expiration}")
	private String expiration;
	@Value("${policlass.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		User logado = (User) authentication.getPrincipal();

		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

		String token = Jwts.builder().setIssuer("API").setSubject(logado.getId().toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS512, secret).compact();

		return token;
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserID(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return Long.parseLong(claims.getSubject());
		} catch (Exception e) {
			return null;
		}
	}

	public String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}

	public Long recuperarUserIdFromRequest(HttpServletRequest request) {
		return getUserID(retrieveToken(request));
	}

}
