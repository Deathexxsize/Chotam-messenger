package com.deathexxsize.TheTwitterKiller.dto.userDTOs;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Ответ с профилем пользователя")
public record UserProfileResponse (
        @Schema(
                description = "Имя пользователя",
                example = "john_doe",
                required = true
        )
        String username,

        @Schema(
                description = "Количество подписчиков",
                example = "150",
                required = true,
                minimum = "0"
        )
        int followers,

        @Schema(
                description = "Количество подписок",
                example = "85",
                required = true,
                minimum = "0"
        )
        int following,

        @Schema(
                description = "Список твитов пользователя",
                required = true
        )
        List<TweetDTO> tweets

) {}
