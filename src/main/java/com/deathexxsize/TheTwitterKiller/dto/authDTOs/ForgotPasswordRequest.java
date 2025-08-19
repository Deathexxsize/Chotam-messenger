package com.deathexxsize.TheTwitterKiller.dto.authDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Поле для запроса на сброс пароля")
public record ForgotPasswordRequest(
        @Schema(description = "имя пользователя", example = "Mark")
        String username
) {}
