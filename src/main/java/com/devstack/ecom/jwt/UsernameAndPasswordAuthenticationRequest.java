package com.devstack.ecom.jwt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

}
