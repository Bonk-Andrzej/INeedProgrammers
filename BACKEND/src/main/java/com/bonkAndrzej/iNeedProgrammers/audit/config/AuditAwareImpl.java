package com.bonkAndrzej.iNeedProgrammers.audit.config;

import com.bonkAndrzej.iNeedProgrammers.security.Auth;
import com.bonkAndrzej.iNeedProgrammers.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@AllArgsConstructor
public class AuditAwareImpl implements AuditorAware<String> {

    private final Auth auth;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(auth.getCurrentUserEmail()
            .orElse(UtilConstants.SYSTEM_ACCOUNT));
    }
}
