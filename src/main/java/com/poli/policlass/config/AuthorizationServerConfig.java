package com.poli.policlass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    static final int MINUTE_IN_SECONDS = 60;
    static final int ACCESS_MINUTES = 60;
    static final int ACCESS_TOKEN_DAYS = 1;
    static final int REFRESH_DAY_AMOUNTS = 5;
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = ACCESS_MINUTES * ACCESS_TOKEN_DAYS * MINUTE_IN_SECONDS;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = REFRESH_DAY_AMOUNTS * ACCESS_TOKEN_DAYS * MINUTE_IN_SECONDS;
    /*
     * Client & Token informations
     */
    static final String SIGNING_KEY_JWT = "latreta"; //TODO: mudar para outro lugar
    static final String CLIENT_ID = "angular";
    static final String CLIENT_SECRET = "$2a$12$o.O3FOBovWbrT5yIUAqiV.QW3hCuNwd671w.zKLgp/LT0V9MDN7J6";
    static final String[] GRANT_TYPES = {"password", "authorization_code", "refresh_token", "implicit"};
    static final String[] SCOPES = { "read", "write" };

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY_JWT);
        return converter;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPES)
                .scopes(SCOPES)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
