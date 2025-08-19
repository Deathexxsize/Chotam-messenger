package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Ответ после написания комментария")
public record CreateCommentResponse (
        @Schema(description = "ID созданного комментария", example = "123")
        int commentId,

        @Schema(description = "Автор комментария", example = "username")
        String author,

        @Schema(description = "ID твита", example = "456")
        int tweetId,

        @Schema(description = "Содержание комментария", example = "Отличный твит!")
        String content,

        @Schema(description = "Время создания комментария", example = "2024-01-15T14:30:00")
        LocalDateTime timestamp
) { }
