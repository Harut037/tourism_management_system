package com.example.tourism_management_system.security.config;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    
    
    private final UserRepository  userRepository;
    
    @Autowired
    public UserDetailsServiceImpl (UserRepository userRepository) {this.userRepository = userRepository;}
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional <UserEntity>  userEntity = userRepository.findByEmail(email);
        return userEntity.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by this email" + email));
    }
}