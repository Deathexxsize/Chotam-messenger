package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ответ с лентой твитов")
public record TweetFeedResponse (
        @Schema(
                description = "Уникальный идентификатор твита",
                example = "123",
                required = true
        )
        Integer id,

        @Schema(
                description = "Имя пользователя автора твита",
                example = "john_doe",
                required = true
        )
        String username,

        @Schema(
                description = "Заголовок твита",
                example = "Мой интересный твит",
                required = true,
                maxLength = 100
        )
        String title,

        @Schema(
                description = "Содержание твита",
                example = "Это содержимое моего интересного твита!",
                required = true,
                maxLength = 280
        )
        String content,

        @Schema(
                description = "Количество лайков твита",
                example = "42",
                required = true,
                minimum = "0"
        )
        Long likeCount,

        @Schema(
                description = "Дата и время создания твита",
                example = "2024-01-15T14:30:00",
                required = true,
                type = "string",
                format = "date-time"
        )
        LocalDateTime createdAt
) {}
