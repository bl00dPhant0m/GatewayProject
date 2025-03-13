package ru.bl00dphant0m.securityservice.configuration;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Set;

@Builder
public class CustomUserPrincipal implements UserDetails {
    private String username;
    private String password;
    private Set<GrantedAuthority> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
