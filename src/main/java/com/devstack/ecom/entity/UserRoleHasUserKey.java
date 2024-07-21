package com.devstack.ecom.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserKey {
    @Column(name = "user_id", length = 80)
    private String user;
    @Column(name = "role_id", length = 80)
    private String userRole;
}
