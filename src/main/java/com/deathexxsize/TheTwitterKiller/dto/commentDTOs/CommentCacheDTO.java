package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;

import java.time.LocalDateTime;

public record CommentCacheDTO(
        Integer id,
        Integer tweetId,
        Integer authorId,
        String authorUsername,
        LocalDateTime createdAt,
        String content
) {}
