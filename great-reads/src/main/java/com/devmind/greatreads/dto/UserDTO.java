package com.devmind.greatreads.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String role;
    private String password;
    private String firstName;
    private String lastName;
}