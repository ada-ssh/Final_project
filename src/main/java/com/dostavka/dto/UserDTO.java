package com.dostavka.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;

}
