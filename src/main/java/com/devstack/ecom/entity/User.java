package com.devstack.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @Column(name = "user_id", length = 80)
    private String userId;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "display_name", length = 80, nullable = false)
    private String displayName;

    @Column(name = "is_account_non_expired", columnDefinition = "TINYINT")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked", columnDefinition = "TINYINT")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired", columnDefinition = "TINYINT")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled", columnDefinition = "TINYINT")
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(
            name = "user_user_role", // Join table name
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key column in the join table for User
            inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key column in the join table for UserRole
    )
    private Set<UserRole> roles;

}
