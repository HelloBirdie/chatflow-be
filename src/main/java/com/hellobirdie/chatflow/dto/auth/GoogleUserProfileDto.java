package com.hellobirdie.chatflow.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleUserProfileDto {

    private String email;
    private String name;
    private String picture;
    private String locale;
    private String familyName;
    private String givenName;
    private String id;
}
