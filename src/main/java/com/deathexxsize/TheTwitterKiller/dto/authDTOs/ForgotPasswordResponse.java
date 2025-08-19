package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ на сброс пароля пользователю")
public record ForgotPasswordResponse (
        @Schema(description = "Сообщение пользователю", example = "На вашу почту было отправлено код верификации")
         String message,
         @Schema(description = "uuid токен доступа", example = "a51bf672-d812...")
         String token
) {}
