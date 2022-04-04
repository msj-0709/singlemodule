package com.example.singlemodule.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {
    @Valid
    @NotBlank
    private String username;

    @Valid
    @NotNull
    private String password;

    @Valid
    @NotBlank
    private String koreanName;
}