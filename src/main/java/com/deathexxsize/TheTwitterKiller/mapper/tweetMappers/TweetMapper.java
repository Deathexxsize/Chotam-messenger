package com.deathexxsize.TheTwitterKiller.mapper.tweetMappers;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    @Mapping(source = "user.username", target = "username")
    CreateTweetResponse toCreateTweetDTO(Tweet tweet);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "createdAt", target = "timestamp")
    AllTweetsResponse toAllTweetsDTO(Tweet tweet);

    List<AllTweetsResponse> toAllTweetsDTO(List<Tweet> tweets);
}
