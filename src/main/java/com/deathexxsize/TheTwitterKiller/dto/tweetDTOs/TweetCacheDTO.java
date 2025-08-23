package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import java.time.LocalDateTime;

public record TweetCacheDTO (
        Integer id,
        Integer userId,
        String username,
        String title,
        String content,
        LocalDateTime createdAt
) {}
