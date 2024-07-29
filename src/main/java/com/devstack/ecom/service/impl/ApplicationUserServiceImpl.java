package com.devstack.ecom.service.impl;

import com.devstack.ecom.auth.ApplicationUser;
import com.devstack.ecom.entity.User;
import com.devstack.ecom.entity.UserRoleHasUser;
import com.devstack.ecom.exception.EntryNotFoundException;
import com.devstack.ecom.repo.UserRepo;
import com.devstack.ecom.repo.UserRoleHasUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.devstack.ecom.security.ApplicationUserRole.*;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> selectedUser = userRepo.findByEmail(username);
        if (selectedUser.isEmpty()) {
            throw new EntryNotFoundException(String.format("username %s not found"));

        }

        List<UserRoleHasUser> roleList = userRoleHasUserRepo.findByUser(selectedUser.get().getUserId());
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        for (UserRoleHasUser u : roleList) {
            if (u.getUserRole().getRoleName().equals("USER")) {
                grantedAuthorities.addAll(USER.grantedAuthorities());
            }
            if (u.getUserRole().getRoleName().equals("CUSTOMER")) {
                grantedAuthorities.addAll(CUSTOMER.grantedAuthorities());
            }
            if (u.getUserRole().getRoleName().equals("ADMIN")) {
                grantedAuthorities.addAll(ADMIN.grantedAuthorities());
            }
            if (u.getUserRole().getRoleName().equals("MANAGER")) {
                grantedAuthorities.addAll(MANAGER.grantedAuthorities());
            }
        }

//        ApplicationUser user = new ApplicationUser
                return new ApplicationUser(
                grantedAuthorities,
                selectedUser.get().getPassword(),
                selectedUser.get().getEmail(),
                selectedUser.get().isAccountNonExpired(),
                selectedUser.get().isAccountNonLocked(),
                selectedUser.get().isCredentialsNonExpired(),
                selectedUser.get().isEnabled()
        );
    }
}
