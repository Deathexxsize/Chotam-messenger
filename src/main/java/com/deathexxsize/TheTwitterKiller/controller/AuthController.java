package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.ForgotPasswordRequest;
import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterRequest;
import com.deathexxsize.TheTwitterKiller.dto.authDTOs.ResetPasswordRequest;
import com.deathexxsize.TheTwitterKiller.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity< ? > register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity< ? > login(@RequestBody com.deathexxsize.TheTwitterKiller.dto.authDTO.LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.verify(loginRequest));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity< ? > forgotPassword(
            @RequestBody ForgotPasswordRequest forgotPasswordRequest
    ) {
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordRequest.getUsername()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity< ? > resetPassword(@RequestBody ResetPasswordRequest request){
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
