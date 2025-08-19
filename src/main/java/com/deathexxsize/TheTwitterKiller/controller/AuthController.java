package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.*;
import com.deathexxsize.TheTwitterKiller.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth-controller", description = "Методы управления аунтентификацией / авторизацией")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Зарегистрировать пользователя",
            description = "Возвращает jwt после регистрации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно зарегистрирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "нет доступа к эндпоинту")
            }
    )
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Авторизовать пользователя",
            description = "Возвращает jwt после авторизации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно авторизован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "нет доступа к эндпоинту")
            }
    )
    public ResponseEntity< JwtResponse > login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.verify(loginRequest));
    }

    @PostMapping("/forgot-password")
    @Operation(
            summary = "Запрос на сброс пароля",
            description = "Возвращает uuid токен и сообщение с кодом на почту",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "код верификации отправлен на почту",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ForgotPasswordResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "нет доступа к эндпоинту")
            }
    )
    public ResponseEntity<ForgotPasswordResponse> forgotPassword( @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(authService.forgotPassword(request.username()));
    }

    @PostMapping("/reset-password")
    @Operation(
            summary = "Сброс пароля пользователя",
            description = "Возвращает сообщение об успехе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ваш пароль успешно сброшен",
                            content = @Content(
                                    mediaType = "String",
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "нет доступа к эндпоинту")
            }
    )
    public ResponseEntity< ? > resetPassword(@RequestBody ResetPasswordRequest request){
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
