package com.hospital.system.service.impl;


import com.hospital.system.auth.ApplicationUser;
import com.hospital.system.enity.User;
import com.hospital.system.repo.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.amazonaws.services.kms.model.KeyManagerType.CUSTOMER;
import static com.hospital.system.security.ApplicationUserRole.ADMIN;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;


    public ApplicationUserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepo.findByUsername(username);
        if (byUsername == null) {
            throw new UsernameNotFoundException(String.format("username %s not found", username));
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        switch (byUsername.getUserRole().getRoleName()) {

            case "ADMIN":
                grantedAuthorities = ADMIN.getGrantedAuthorities();
                break;

        }

        ApplicationUser user = new ApplicationUser(
                byUsername.getPassword(),
                byUsername.getUsername(),
                grantedAuthorities,
                byUsername.isAccountNonExpired(),
                byUsername.isAccountNonLocked(),
                byUsername.isCredentialsNonExpired(),
                byUsername.isEnabled()
        );
        return user;
    }
}
