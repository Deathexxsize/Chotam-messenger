package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ при успешном создании твита")
public record CreateTweetResponse (
        @Schema(description = "Имя пользователя автора твита", example = "Tor")
        String username,

        @Schema(description = "Заголовок твита", example = "Мой первый твит")
        String title,

        @Schema(description = "Содержание твита", example = "Это содержимое моего первого твита!")
        String content
) {}
