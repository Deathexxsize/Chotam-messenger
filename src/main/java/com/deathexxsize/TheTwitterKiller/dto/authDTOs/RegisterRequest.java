package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "поля для запроса регистрации")
public record RegisterRequest (
        @Schema(description = "имя пользоваиеля", example = "Robert")
        String username,
        @Schema(description = "электронная почта пользователя", example = "robert@gmail.com")
        String email,
        @Schema(description = "пароль пользователя", example = "robert123")
        String password
) { }
