package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity< ? > register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity< ? > login(@RequestBody com.deathexxsize.TheTwitterKiller.dto.authDTO.LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.verify(loginDTO));
    }

}
