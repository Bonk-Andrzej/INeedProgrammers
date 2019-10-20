package com.bonkAndrzej.iNeedProgrammers.security;

import com.bonkAndrzej.iNeedProgrammers.user.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ToString
public class CurrentUserDetails implements UserDetails {

    private final User user;
    private final Set<GrantedAuthority> rolesAuthority = new HashSet<>();

    public CurrentUserDetails(User user) {
        this.user = user;
        rolesAuthority.add(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAuthority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
