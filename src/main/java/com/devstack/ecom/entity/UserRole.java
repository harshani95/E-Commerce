package com.devstack.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class UserRole {
    @Id
    @Column(name = "role_id", length = 80)
    private String roleId;

    @Column(name = "role_name", length = 100)
    private String roleName;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
