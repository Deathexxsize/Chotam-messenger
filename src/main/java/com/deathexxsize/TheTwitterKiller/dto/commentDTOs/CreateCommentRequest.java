package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание комментария под твитом")
public record CreateCommentRequest (
        @Schema(description = "контент комментария", example = "its comment")
        String content
) { }
