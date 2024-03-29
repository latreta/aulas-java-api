package com.poli.policlass.config;

import com.poli.policlass.model.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        User user = (User) oAuth2Authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put("name", user.getName());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(
                additionalInfo);
        return oAuth2AccessToken;
    }
}
