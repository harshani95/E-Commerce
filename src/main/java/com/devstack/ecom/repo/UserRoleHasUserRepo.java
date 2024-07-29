package com.devstack.ecom.repo;

import com.devstack.ecom.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser, String> {
    List<UserRoleHasUser> findByUser(String user);
}
