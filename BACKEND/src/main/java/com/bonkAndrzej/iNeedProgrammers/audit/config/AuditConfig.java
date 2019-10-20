package com.bonkAndrzej.iNeedProgrammers.audit.config;

import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@AllArgsConstructor
public class AuditConfig {

    private final Auth auth;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditAwareImpl(auth);
    }
}
