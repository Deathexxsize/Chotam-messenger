package com.deathexxsize.TheTwitterKiller.dto.tweetDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTweetRequest {
    private String title;
    private String content;
}
