package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создания твита")
public record CreateTweetRequest (
        @Schema(description = "заголовок твита", example = "Это мой пост!")
        String title,
        @Schema(description = "контент твита", example = "тут уже сам контент твита")
        String content
) { }
