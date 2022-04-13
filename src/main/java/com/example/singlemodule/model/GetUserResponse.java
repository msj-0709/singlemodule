package com.example.singlemodule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserResponse {
    private String userInfoId;

    private String username;

    private String password;

    private String koreanName;
}
