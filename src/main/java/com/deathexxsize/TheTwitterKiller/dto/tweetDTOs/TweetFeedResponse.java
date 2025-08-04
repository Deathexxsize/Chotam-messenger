package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import com.deathexxsize.TheTwitterKiller.model.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetFeedResponse {
    private Integer id;
    private String username;
    private String title;
    private String content;
    private Long likeCount;
    private LocalDateTime createdAt;
}
