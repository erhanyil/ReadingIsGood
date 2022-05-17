package com.example.readingisgood.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TokenRequestDTO {

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Email is mandatory")
    private String password;

}
