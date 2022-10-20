package com.example.server.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegistrationDto {
    private String login;
    private String password;
    private String role;
}
