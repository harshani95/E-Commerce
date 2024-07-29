package com.devstack.ecom.repo;

import com.devstack.ecom.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser, String> {
    List<UserRoleHasUser> findByUser(String user);

    @Query(value = "SELECT * FROM user_role_has_user WHERE user_id = ?1", nativeQuery = true)
    List<UserRoleHasUser> findByUserId(String userId);
}
