package com.example.tourism_management_system.security.config;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    
    private final String                 email;
    private final String                 password;
    private final List<GrantedAuthority> authorities;
    private final Status status;
    
    public UserDetailsImpl (UserEntity userEntity) {
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        this.authorities= Arrays.stream(userEntity.getRoleEntity().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        status = userEntity.getStatus();
    }
    
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities () {
        return authorities;
    }
    
    @Override
    public String getPassword () {
        return password;
    }
    
    @Override
    public String getUsername () {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired () {
        return status != Status.EXPIRED;
    }
    
    @Override
    public boolean isAccountNonLocked () {
        return status != Status.BLOCKED;
    }
    
    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }
    
    @Override
    public boolean isEnabled () {
        return status == Status.ACTIVE;
    }
}