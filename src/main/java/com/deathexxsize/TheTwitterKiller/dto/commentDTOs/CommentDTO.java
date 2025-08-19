package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;
import java.time.LocalDateTime;

public record CommentDTO (
        int commentId,
        String author,
        String content,
        LocalDateTime timestamp
) { }
