package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ со всеми твитами пользователя")
public record AllTweetsResponse (
        @Schema(
                description = "Имя пользователя автора твита",
                example = "john_doe",
                required = true
        )
        String username,

        @Schema(
                description = "Заголовок твита",
                example = "Мой первый твит",
                required = true,
                maxLength = 128
        )
        String title,

        @Schema(
                description = "Содержание твита",
                example = "Это содержимое моего твита!",
                required = true,
                maxLength = 1024
        )
        String content,

        @Schema(
                description = "Дата и время создания твита",
                example = "2024-01-15T14:30:00",
                required = true,
                type = "string",
                format = "date-time"
        )
        LocalDateTime timestamp
) {}
