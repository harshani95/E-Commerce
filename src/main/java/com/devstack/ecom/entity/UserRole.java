package com.devstack.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserRole {
    @Id
    @Column(name = "role_id", length = 80)
    private String roleId;

    @Column(name = "role_nae", length = 100)
    private String roleName;

    @Column(name = "description", length = 100)
    private String description;


    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private Set<UserRoleHasUser> userRoleHasUsers;
}
