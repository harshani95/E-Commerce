package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestUserRoleDto;

public interface UserRoleService {
    public void create(RequestUserRoleDto dto);
    public void initializerUserRoles();
}
