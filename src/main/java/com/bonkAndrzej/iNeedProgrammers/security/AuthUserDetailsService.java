package com.bonkAndrzej.iNeedProgrammers.security;

import com.bonkAndrzej.iNeedProgrammers.user.User;
import com.bonkAndrzej.iNeedProgrammers.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = userService.findOneByUsername(email)
            .orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));
        return new AuthUserDetails(user);
    }
}
