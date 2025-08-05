package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.service.AuthService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@Tag(name = "Auth API", description = "Управление авторизацией")
public class AuthController {



    @Autowired
    private AuthService authService;

    @PostMapping("/register")
//    @Operation(
//            summary = "Зарегистрировать пользователя",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Пользователь зарегестрирован"),
//            }
//    )
    public ResponseEntity< ? > register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity< ? > login(@RequestBody com.deathexxsize.TheTwitterKiller.dto.authDTO.LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.verify(loginDTO));
    }

}
