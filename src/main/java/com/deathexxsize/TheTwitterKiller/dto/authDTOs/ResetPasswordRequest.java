package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Поля для сброса пароля")
public record ResetPasswordRequest (
        @Schema(description = "6-х код верификации с почты пользователя", example = "457890")
        int code,
        @Schema(description = "пароль пользователя", example = "maxim123")
        String password,
        @Schema(description = "повторный ввод пароля", example = "maxim123")
        String confirmPassword,
        @Schema(description = "uuid токен чтобы сервер узнал", example = "a51bf672-d812...")
        String token
) {}
