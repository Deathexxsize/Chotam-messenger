package com.deathexxsize.TheTwitterKiller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private String username;
    private int followers;
    private int following;
    private List<TweetDTO> tweets;
}
