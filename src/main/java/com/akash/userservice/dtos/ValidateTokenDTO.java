package com.akash.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDTO {
    private String token;
    private Long userId;
}
