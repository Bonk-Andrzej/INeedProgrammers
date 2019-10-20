package com.bonkAndrzej.iNeedProgrammers.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider TokenProvider;

    public JWTConfigurer(TokenProvider TokenProvider) {
        this.TokenProvider = TokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(TokenProvider);
        log.info("JWTConfigurer config ");
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
