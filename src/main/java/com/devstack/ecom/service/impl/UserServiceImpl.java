package com.devstack.ecom.service.impl;

import com.devstack.ecom.dto.request.RequestUserDto;
import com.devstack.ecom.entity.User;
import com.devstack.ecom.entity.UserRole;
import com.devstack.ecom.exception.DuplicateEntryException;
import com.devstack.ecom.exception.EntryNotFoundException;
import com.devstack.ecom.repo.RoleRepo;
import com.devstack.ecom.repo.UserRepo;
import com.devstack.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(RequestUserDto dto, String roleType) {
        Optional<User> byEmail = userRepo.findByEmail(dto.getEmail());
        if (byEmail.isPresent()) {
            throw new DuplicateEntryException("user already exists");
        }


        Optional<UserRole> selectedRole = roleRepo.findUserRoleByRoleName(roleType);
        if (selectedRole.isEmpty()) {
            throw new EntryNotFoundException("Role not found");
        }

        HashSet<UserRole> objects = new HashSet<>();
        objects.add(selectedRole.get());

        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(dto.getEmail())
                .displayName(dto.getDisplayName())
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .roles(objects)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        userRepo.save(user);
    }

    @Override
    public void initializeAdmin() {
        Optional<User> byEmail = userRepo.findByEmail("admin@gmail.com");
        if (byEmail.isEmpty()) {

            Optional<UserRole> selectedRole = roleRepo.findUserRoleByRoleName("ADMIN");
            if (selectedRole.isEmpty()) {
                throw new EntryNotFoundException("Role not found");
            }

            HashSet<UserRole> objects = new HashSet<>();
            objects.add(selectedRole.get());
            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .email("admin@gmail.com")
                    .displayName("admin")
                    .isEnabled(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonExpired(true)
                    .roles(objects)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            userRepo.save(user);
        }
    }
}
