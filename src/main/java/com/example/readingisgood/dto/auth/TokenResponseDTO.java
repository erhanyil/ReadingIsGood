package com.example.readingisgood.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenResponseDTO {

    private String token;

}
