package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String email;
    private String password;
}
