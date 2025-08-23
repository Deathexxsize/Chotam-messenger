package com.deathexxsize.TheTwitterKiller.mapper.tweetMappers;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetCacheDTO;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetCacheMapper {

    @Mapping(target = "user", expression = "java(mapUser(dto.userId(), dto.username()))")
    Tweet toTweet(TweetCacheDTO dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    TweetCacheDTO toTweetCache(Tweet tweet);

    List<TweetCacheDTO> toTweetCacheDTOs(List<Tweet> tweets);
    List<Tweet> toTweetsFromCache(List<TweetCacheDTO> tweetCacheDTOs);

    default User mapUser(Integer id, String username) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        u.setUsername(username);
        return u;
    }
}



