package com.devstack.ecom.service;

import com.devstack.ecom.dto.request.RequestUserDto;

public interface UserService {
    public void create(RequestUserDto dto, String roleType);
}
