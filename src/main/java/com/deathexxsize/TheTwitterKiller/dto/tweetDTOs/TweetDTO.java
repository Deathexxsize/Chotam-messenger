package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO твита")
public record TweetDTO (
        @Schema(
                description = "Заголовок твита",
                example = "Мой первый твит",
                maxLength = 128
        )
        String title,

        @Schema(
                description = "Содержание твита",
                example = "Это содержимое моего твита!",
                maxLength = 1024
        )
        String content,

        @Schema(
                description = "Дата и время создания твита",
                example = "2024-01-15T14:30:00",
                type = "string",
                format = "date-time"
        )
        LocalDateTime createdAt
) {}
