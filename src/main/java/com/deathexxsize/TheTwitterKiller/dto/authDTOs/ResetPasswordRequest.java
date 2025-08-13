package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    private int code;
    private String password;
    private String confirmPassword;
    private String token;
}
