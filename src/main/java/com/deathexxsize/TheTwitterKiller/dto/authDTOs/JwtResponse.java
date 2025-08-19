package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с jwt токеном после регистрации / авторизации")
public record JwtResponse(
        @Schema(description = "jwt-токен для доступа", example = "eyJhbGciOiJIUzI1...")
        String jwt
) {}
