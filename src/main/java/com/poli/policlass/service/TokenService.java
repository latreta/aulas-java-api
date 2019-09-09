package com.poli.policlass.service;

import com.poli.policlass.model.entity.User;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private UserService userService;

    public User recupera(String token) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setSkipSignatureVerification().build();
        try{
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            final Map<String, Object> claimsMap = jwtClaims.getClaimsMap();
            String email = (String) claimsMap.get("user_name");
            Long expirationMillis = (Long) claimsMap.get("exp");
            Date expirationDate = new Date(expirationMillis * 1000);
            if(new Date().before(expirationDate)){
                return userService.buscarPorEmail(email);
            }
        } catch (InvalidJwtException e) {
            e.printStackTrace();
        }

        return null;
    }
}
