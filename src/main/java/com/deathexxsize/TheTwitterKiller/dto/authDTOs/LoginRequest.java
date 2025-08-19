package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "поля для запроса на автризации")
public record LoginRequest(
        @Schema(description = "имя пользователя", example = "Tobi")
         String username,
         @Schema(description = "пароль пользователя", example = "tobi123")
         String password
) { }
