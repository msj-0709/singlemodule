package com.example.singlemodule.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class CreateBoardRequest {
    @Valid
    @NotBlank
    private String title;

    @Valid
    @NotBlank
    private String contents;

    @Valid
    @NotBlank
    private String userInfoId;
}