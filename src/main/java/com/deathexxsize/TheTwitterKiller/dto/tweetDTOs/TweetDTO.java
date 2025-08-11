package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
